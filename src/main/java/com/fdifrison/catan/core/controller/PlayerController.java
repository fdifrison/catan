package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.filter.PlayerFilter;
import com.fdifrison.catan.core.service.PlayerService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("{id}")
    public PlayerDTO getPlayer(@PathVariable long id) {
        return playerService.getPlayer(id);
    }

    @PutMapping("{id}")
    public void updatePlayer(@PathVariable long id, @RequestBody PlayerDTO playerDTO) {
        playerService.updatePlayer(id, playerDTO);
    }

    @GetMapping
    public Page<PlayerDTO> search(
            @ParameterObject PlayerFilter filter,
            @PageableDefault(sort = "id", size = 5) @ParameterObject Pageable pageable) {
        return playerService.search(filter, pageable);
    }
}
