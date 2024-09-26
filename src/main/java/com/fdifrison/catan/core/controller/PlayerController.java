package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long newPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.newPlayer(playerDTO);
    }
}
