package com.fdifrison.catan.core.dto;

public record InitTurnDTO(long gameId,
                          long playerId,
                          int outcome) {
}

