package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.dto.mapper.PlayerMapper;
import com.fdifrison.catan.core.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public long newPlayer(PlayerDTO playerDTO) {
        var entity = PlayerMapper.INSTANCE.toEntity(playerDTO);
        return playerRepository.save(entity).getId();
    }
}
