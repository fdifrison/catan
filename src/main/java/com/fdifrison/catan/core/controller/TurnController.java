package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.service.TurnService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("turn")
public class TurnController {

    private final TurnService turnService;

    public TurnController(TurnService turnService) {
        this.turnService = turnService;
    }
}
