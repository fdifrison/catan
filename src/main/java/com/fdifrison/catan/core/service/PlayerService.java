package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.dto.mapper.PlayerMapper;
import com.fdifrison.catan.core.entity.Player;
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
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public long newPlayer(PlayerDTO playerDTO) {
        var entity = playerMapper.toEntity(playerDTO);
        return playerRepository.save(entity).getId();
    }

    public PlayerDTO getPlayerDTO(long id) {
        return playerMapper.toDto(findPlayerById(id));
    }

    protected Player findPlayerById(long id) {
        return playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
    }

    @Transactional
    public void updatePlayer(long id, PlayerDTO playerDTO) {
        var player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
        playerMapper.update(player, playerDTO);
    }

    public Page<PlayerDTO> search(PlayerFilter filter, Pageable pageable) {
        return playerRepository
                .findAll(new PlayerSpecification(filter), pageable)
                .map(playerMapper::toDto);
    }
}
