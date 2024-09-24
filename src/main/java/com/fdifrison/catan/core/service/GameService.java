package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.entity.Game;
import com.fdifrison.catan.core.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public int startGame() {
        return gameRepository.save(new Game()).getId();
    }

    public GameDTO endGame(int id) {
        var reference = gameRepository.getReferenceById(id);

        return null;
    }
}
