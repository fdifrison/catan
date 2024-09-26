package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.repository.GameRepository;
import com.fdifrison.catan.core.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Long createNewGame(List<Long> players) {
        return null;
    }
}
