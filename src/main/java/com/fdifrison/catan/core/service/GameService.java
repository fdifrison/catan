package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.dto.GameStateDTO;
import com.fdifrison.catan.core.dto.PlayerOrderDTO;
import com.fdifrison.catan.core.dto.mapper.GameMapper;
import com.fdifrison.catan.core.entity.Game;
import com.fdifrison.catan.core.entity.Player;
import com.fdifrison.catan.core.entity.Turn;
import com.fdifrison.catan.core.exception.GameMalformedException;
import com.fdifrison.catan.core.repository.GameRepository;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;

    public GameService(GameRepository gameRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }

    @Transactional
    public long createGame(GameSetupDTO gameSetup) {
        var game = GameMapper.INSTANCE.initEntity(gameSetup);
        var savedGame = gameRepository.save(game);
        parsePlayers(gameSetup.players()).forEachOrdered(player -> {
            var turn = createTurn(player, game);
            savedGame.addTurn(turn);
        });
        savedGame.setNumberOfPlayers(savedGame.getTurns().size());
        return savedGame.getId();
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

    private Stream<Player> parsePlayers(List<PlayerOrderDTO> players) {
        if (players.stream().distinct().count() < 2) {
            throw new GameMalformedException("Not enough player, min 2 required");
        }
        return players.stream().sorted().map(PlayerOrderDTO::getPlayerId).map(playerService::getPlayer);
    }

    public GameStateDTO getGameStatus(long gameId) {
        return null;
    }
}
