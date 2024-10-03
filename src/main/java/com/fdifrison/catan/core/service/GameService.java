package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.dto.mapper.GameMapper;
import com.fdifrison.catan.core.dto.mapper.GamePlayerMapper;
import com.fdifrison.catan.core.entity.Game;
import com.fdifrison.catan.core.entity.GamePlayer;
import com.fdifrison.catan.core.entity.Player;
import com.fdifrison.catan.core.entity.Turn;
import com.fdifrison.catan.core.repository.GameRepository;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final GamePlayerService gamePlayerService;
    private final GameMapper gameMapper;
    private final GamePlayerMapper gamePlayerMapper;

    public GameService(
            GameRepository gameRepository,
            PlayerService playerService,
            GamePlayerService gamePlayerService,
            GameMapper gameMapper,
            GamePlayerMapper gamePlayerMapper) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.gamePlayerService = gamePlayerService;
        this.gameMapper = gameMapper;
        this.gamePlayerMapper = gamePlayerMapper;
    }

    @Transactional
    public long createGame(GameSetupDTO gameSetup) {
        var game = gameMapper.initEntity(gameSetup.gameInfo());
        var savedGame = gameRepository.saveAndFlush(game);
        parsePlayers(gameSetup.playersInfo(), savedGame.getId())
                .map(p -> createTurn(p, game))
                .forEach(savedGame::addTurn);
        return savedGame.getId();
    }

    private Stream<Player> parsePlayers(List<GameSetupDTO.GamePlayerInfoDTO> players, long gameId) {
        return gamePlayerMapper.updateDto(players, gameId).stream()
                .sorted()
                .map(gamePlayerService::createGamePlayer)
                .map(GamePlayer::getPlayerId)
                .map(playerService::getPlayer);
    }

    private Turn createTurn(Player player, Game game) {
        return new Turn()
                .setGame(game)
                .setPlayer(player)
                .setOutcome(0)
                .setDevelopCardDrawn(0)
                .setKnightCardPlayed(false)
                .setLongestRoad(false)
                .setLargestArmy(false)
                .setRoadsBuilt(2)
                .setColoniesBuilt(2)
                .setCitiesBuilt(0);
    }

    public Page<GameDTO> search(Pageable pageable) {
        return gameRepository.findAll(pageable).map(gameMapper::toDto);
    }
}
