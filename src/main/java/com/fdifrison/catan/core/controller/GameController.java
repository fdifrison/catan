package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.service.GameService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public Page<GameDTO> searchGames(@PageableDefault(sort = "id", size = 5) @ParameterObject Pageable pageable) {
        return gameService.search(pageable);
    }
}
