package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.TurnDTO;
import com.fdifrison.catan.core.entity.Turn;
import com.fdifrison.catan.core.service.TurnService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("turn")
public class TurnController {

    private final TurnService turnService;

    public TurnController(TurnService turnService) {
        this.turnService = turnService;
    }

}
