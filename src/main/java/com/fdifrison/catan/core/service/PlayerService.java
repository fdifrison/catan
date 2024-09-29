package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.dto.mapper.PlayerMapper;
import com.fdifrison.catan.core.exception.PlayerNotFoundException;
import com.fdifrison.catan.core.filter.PlayerFilter;
import com.fdifrison.catan.core.repository.PlayerRepository;
import com.fdifrison.catan.core.repository.specification.PlayerSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PlayerDTO> search(PlayerFilter filter, Pageable pageable) {
        return playerRepository
                .findAll(new PlayerSpecification(filter), pageable)
                .map(PlayerMapper.INSTANCE::toDto);
    }
}
