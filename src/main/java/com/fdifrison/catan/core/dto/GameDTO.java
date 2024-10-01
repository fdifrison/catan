package com.fdifrison.catan.core.dto;

import com.fdifrison.catan.core.entity.PlayerScore;
import java.time.Instant;
import java.util.List;

public record GameDTO(long id, Instant startTimestamp, Instant endTimestamp, List<PlayerScore> playerScores) {}
