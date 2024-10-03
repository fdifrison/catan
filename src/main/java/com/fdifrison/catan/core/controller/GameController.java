package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.dto.GameStateDTO;
import com.fdifrison.catan.core.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long createGame(@Valid @RequestBody GameSetupDTO gameSetup) {
        return gameService.createGame(gameSetup);
    }

    @GetMapping("{gameId}")
    public GameStateDTO getGameStatus(@PathVariable long gameId) {
        return null;
    }
}
