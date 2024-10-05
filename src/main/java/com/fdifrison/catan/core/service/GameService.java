package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.dto.TurnDTO;
import com.fdifrison.catan.core.dto.mapper.GameMapper;
import com.fdifrison.catan.core.dto.mapper.GamePlayerMapper;
import com.fdifrison.catan.core.entity.Game;
import com.fdifrison.catan.core.entity.GamePlayer;
import com.fdifrison.catan.core.entity.Player;
import com.fdifrison.catan.core.exception.GameNotFoundException;
import com.fdifrison.catan.core.repository.GameRepository;
import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final GamePlayerService gamePlayerService;
    private final TurnService turnService;
    private final GameMapper gameMapper;
    private final GamePlayerMapper gamePlayerMapper;
    private final StatisticsService statisticsService;

    public GameService(
            GameRepository gameRepository,
            PlayerService playerService,
            GamePlayerService gamePlayerService,
            TurnService turnService,
            GameMapper gameMapper,
            GamePlayerMapper gamePlayerMapper,
            StatisticsService statisticsService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.gamePlayerService = gamePlayerService;
        this.turnService = turnService;
        this.gameMapper = gameMapper;
        this.gamePlayerMapper = gamePlayerMapper;
        this.statisticsService = statisticsService;
    }

    @Transactional
    public long createGame(GameSetupDTO gameSetup) {
        var game = gameMapper.initEntity(gameSetup.gameInfo());
        var savedGame = gameRepository.saveAndFlush(game);
        parsePlayers(gameSetup.playersInfo(), savedGame.getId())
                .map(p -> turnService.createFirstTurn(p, game))
                .forEach(savedGame::addTurn);
        return savedGame.getId();
    }

    private Stream<Player> parsePlayers(List<GameSetupDTO.GamePlayerInfoDTO> players, long gameId) {
        return gamePlayerMapper.updateDto(players, gameId).stream()
                .sorted()
                .map(gamePlayerService::createGamePlayer)
                .map(GamePlayer::getPlayerId)
                .map(playerService::findPlayerById);
    }

    public Page<GameDTO.GameInfoDTO> search(Pageable pageable) {
        return gameRepository
                .findAll(pageable)
                .map(game -> gameMapper.toDto(
                        game,
                        statisticsService.countTurns(
                                game.getId(), game.getGamePlayers().size())));
    }

    protected Game findGameById(long id) {
        return gameRepository.findWithGamePlayersById(id).orElseThrow(GameNotFoundException::new);
    }

    public GameDTO getGameDTOByGameId(long id) {
        var game = findGameById(id);
        var gameInfoDTO = gameMapper.toDto(
                game, statisticsService.countTurns(id, game.getGamePlayers().size()));
        var gamePlayerDTOs =
                statisticsService.computeGamePlayerStatistics(id, getGamePlayerDTOS(game.getGamePlayers()));
        return new GameDTO(gameInfoDTO, gamePlayerDTOs);
    }

    private List<GameDTO.GamePlayerDTO> getGamePlayerDTOS(List<GamePlayer> gamePlayers) {
        var players = gamePlayers.stream()
                .map(GamePlayer::getPlayerId)
                .map(playerService::findPlayerById)
                .toList();
        return StreamUtils.zip(gamePlayers.stream(), players.stream(), gamePlayerMapper::toDto)
                .toList();
    }

    @Transactional
    public void newTurn(long id, TurnDTO turnDTO) {
        var game = findGameById(id);
        var turn = turnService.newTurn(turnDTO, game);
        game.addTurn(turn);
    }

    @Transactional
    public void endGame(long id, List<GameDTO.GamePlayerDTO> players) {
        Game game = findGameById(id).setEndTimestamp(Instant.now());
        game.getGamePlayers().forEach(gamePlayer -> {
            var matchByPlayerId = players.stream()
                    .filter(player -> player.playerId() == gamePlayer.getPlayerId())
                    .toList()
                    .getFirst();
            gamePlayerMapper.updateEntity(gamePlayer, matchByPlayerId);
        });
    }
}
