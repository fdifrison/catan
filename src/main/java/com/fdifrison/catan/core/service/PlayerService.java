package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.dto.mapper.PlayerMapper;
import com.fdifrison.catan.core.entity.Player;
import com.fdifrison.catan.core.exception.PlayerNotFoundException;
import com.fdifrison.catan.core.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public PlayerDTO getPlayer(long id) {
        var player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
        return PlayerMapper.INSTANCE.toDto(player);

    }

    @Transactional
    public void updatePlayer(long id, PlayerDTO playerDTO) {
        var player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
        PlayerMapper.INSTANCE.update(player, playerDTO);
    }
}
