package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.repository.TurnRepository;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    private final TurnRepository turnRepository;

    public TurnService(TurnRepository turnRepository) {
        this.turnRepository = turnRepository;
    }
}
