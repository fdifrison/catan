package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int startGame() {
        return gameService.startGame();
    }

    @PutMapping
    public GameDTO endGame(int id) {
        return gameService.endGame(id);
    }
}
