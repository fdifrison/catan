package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.dto.TurnDTO;
import com.fdifrison.catan.core.service.GameService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public long createGame(@RequestBody @Valid GameSetupDTO gameSetup) {
        return gameService.createGame(gameSetup);
    }

    @GetMapping("{id}")
    public GameDTO findGameById(@PathVariable long id) {
        return gameService.getGameDTOByGameId(id);
    }

    @GetMapping
    public Page<GameDTO.GameInfoDTO> searchGames(
            @PageableDefault(sort = "startTimestamp", direction = Sort.Direction.DESC, size = 5) @ParameterObject
                    Pageable pageable) {
        return gameService.search(pageable);
    }

    @PostMapping("{id}/turn")
    @ResponseStatus(HttpStatus.CREATED)
    public void newTurn(@PathVariable long id, @RequestBody @Valid TurnDTO turnDTO) {
        gameService.newTurn(id, turnDTO);
    }
}
