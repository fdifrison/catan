package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.TurnDTO;
import com.fdifrison.catan.core.dto.mapper.TurnMapper;
import com.fdifrison.catan.core.entity.Game;
import com.fdifrison.catan.core.entity.GamePlayer;
import com.fdifrison.catan.core.entity.Player;
import com.fdifrison.catan.core.entity.Turn;
import com.fdifrison.catan.core.entity.projection.PlayerDiceRollsCount;
import com.fdifrison.catan.core.exception.TurnMalformedException;
import com.fdifrison.catan.core.repository.TurnRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    private final TurnRepository turnRepository;
    private final PlayerService playerService;
    private final TurnMapper turnMapper;

    public TurnService(TurnRepository turnRepository, PlayerService playerService, TurnMapper turnMapper) {
        this.turnRepository = turnRepository;
        this.playerService = playerService;
        this.turnMapper = turnMapper;
    }

    protected Turn createFirstTurn(Player player, Game game) {
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

    protected Turn newTurn(TurnDTO turnDTO, Game game) {
        validateTurn(turnDTO, game);
        var player = playerService.findPlayerById(turnDTO.playerId());
        var turn = turnMapper.toEntity(turnDTO);
        return turnRepository.save(turn.setGame(game).setPlayer(player));
    }

    private void validateTurn(TurnDTO turnDTO, Game game) {
        var playerNotInGame = game.getGamePlayers().stream()
                .map(GamePlayer::getPlayerId)
                .noneMatch(pId -> pId.equals(turnDTO.playerId()));
        if (playerNotInGame) {
            throw new TurnMalformedException("The player associated with this turn does not belong to the game");
        }
    }

    public List<PlayerDiceRollsCount> findDiceCountByGameId(long gameId) {
        return turnRepository.findDiceCountByGameId(gameId);
    }

    public List<PlayerDiceRollsCount> findOverallDiceCountByPlayerId(long playerId) {
        return turnRepository.findOverallDiceCountByPlayerId(playerId);
    }

    public long countByGameId(long gameId) {
        return turnRepository.countByGameId(gameId);
    }
}
