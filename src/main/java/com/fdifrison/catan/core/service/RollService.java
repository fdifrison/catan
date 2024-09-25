package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.repository.RollRepository;
import org.springframework.stereotype.Service;

@Service
public class RollService {

    private final RollRepository rollRepository;

    public RollService(RollRepository rollRepository) {
        this.rollRepository = rollRepository;
    }
}
