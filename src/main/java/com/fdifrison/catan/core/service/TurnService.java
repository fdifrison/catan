package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.EndTurnDTO;
import com.fdifrison.catan.core.dto.InitTurnDTO;
import com.fdifrison.catan.core.dto.mapper.TurnMapper;
import com.fdifrison.catan.core.entity.Turn;
import com.fdifrison.catan.core.exception.GameAlreadyEndedException;
import com.fdifrison.catan.core.exception.TurnNotFoundException;
import com.fdifrison.catan.core.repository.GameRepository;
import com.fdifrison.catan.core.repository.PlayerRepository;
import com.fdifrison.catan.core.repository.TurnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnService {

    private final TurnRepository turnRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public TurnService(
            TurnRepository turnRepository, GameRepository gameRepository, PlayerRepository playerRepository) {
        this.turnRepository = turnRepository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public long initTurn(InitTurnDTO initTurnDTO) {
        var game = gameRepository.getReferenceById(initTurnDTO.gameId());
        if (game.getEndTimestamp() != null) throw new GameAlreadyEndedException();
        var player = playerRepository.getReferenceById(initTurnDTO.playerId());
        var turn = new Turn().setOutcome(initTurnDTO.outcome()).setGame(game).setPlayer(player);
        return turnRepository.save(turn).getId();
    }

    @Transactional
    public void endTurn(long id, EndTurnDTO endTurnDTO) {
        var turn = turnRepository.findById(id).orElseThrow(TurnNotFoundException::new);
        TurnMapper.INSTANCE.updateEntity(turn, endTurnDTO);
    }
}
