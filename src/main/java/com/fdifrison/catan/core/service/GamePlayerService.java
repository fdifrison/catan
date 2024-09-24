package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.repository.GamePlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class GamePlayerService {

    private final GamePlayerRepository gamePlayerRepository;

    public GamePlayerService(GamePlayerRepository gamePlayerRepository) {
        this.gamePlayerRepository = gamePlayerRepository;
    }
}
