package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.dto.mapper.GamePlayerMapper;
import com.fdifrison.catan.core.entity.GamePlayer;
import com.fdifrison.catan.core.repository.GamePlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class GamePlayerService {

    private final GamePlayerRepository gamePlayerRepository;
    private final GamePlayerMapper gamePlayerMapper;

    public GamePlayerService(GamePlayerRepository gamePlayerRepository, GamePlayerMapper gamePlayerMapper) {
        this.gamePlayerRepository = gamePlayerRepository;
        this.gamePlayerMapper = gamePlayerMapper;
    }

    protected GamePlayer createGamePlayer(GameSetupDTO.GamePlayerInfoDTO playerInfo) {
        var entity = gamePlayerMapper.toEntity(playerInfo);
        return gamePlayerRepository.save(entity);
    }
}
