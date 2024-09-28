package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.PlayerScoreDTO;
import com.fdifrison.catan.core.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
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
    public long createNewGame(@RequestBody @Valid List<PlayerScoreDTO> players) {
        return gameService.createNewGame(players);
    }

    @PutMapping("{id}")
    public GameDTO updateScoreAndEndGame(@PathVariable long id, @RequestBody @Valid List<PlayerScoreDTO> players) {
        return gameService.updateScoreAndEndGame(id, players);
    }

    @GetMapping("{id}/score")
    public List<PlayerScoreDTO> getGameRanking(@PathVariable long id) {
        return gameService.getGameRanking(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable long id) {
        gameService.deleteGame(id);
    }
}
