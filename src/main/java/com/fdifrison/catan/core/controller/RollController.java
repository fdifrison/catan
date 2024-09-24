package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.service.RollService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roll")
public class RollController {

    private final RollService rollService;

    public RollController(RollService rollService) {
        this.rollService = rollService;
    }
}
