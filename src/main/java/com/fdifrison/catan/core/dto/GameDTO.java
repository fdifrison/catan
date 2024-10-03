package com.fdifrison.catan.core.dto;

import com.fdifrison.catan.core.entity.Game;
import com.fdifrison.catan.core.entity.GamePlayer;
import java.time.Instant;
import java.util.List;

public record GameDTO(
        long id,
        String gameName,
        Game.GameType gameType,
        int numberOfPlayers,
        int requiredVictoryPoints,
        Instant startTimestamp,
        Instant endTimestamp,
        List<GamePlayer> gamePlayers) {}
