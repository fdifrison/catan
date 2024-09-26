package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.repository.TurnRepository;
import org.springframework.stereotype.Service;

@Service
public class RollService {

    private final TurnRepository turnRepository;

    public RollService(TurnRepository turnRepository) {
        this.turnRepository = turnRepository;
    }
}
