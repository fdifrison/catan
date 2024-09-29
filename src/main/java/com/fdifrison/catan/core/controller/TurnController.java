package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.EndTurnDTO;
import com.fdifrison.catan.core.dto.InitTurnDTO;
import com.fdifrison.catan.core.service.TurnService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("turn")
public class TurnController {

    private final TurnService turnService;

    public TurnController(TurnService turnService) {
        this.turnService = turnService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long newTurn(@RequestBody InitTurnDTO initTurnDTO) {
        return turnService.initTurn(initTurnDTO);
    }

    @PutMapping("{id}")
    public void endTurn(@PathVariable long id ,@RequestBody EndTurnDTO endTurnDTO) {
        turnService.endTurn(id, endTurnDTO);
    }

}
