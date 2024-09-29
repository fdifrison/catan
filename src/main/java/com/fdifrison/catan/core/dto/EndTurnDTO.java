package com.fdifrison.catan.core.dto;

public record EndTurnDTO(int developCardDrawn,
                         boolean developCardPlayed,
                         int roadsBuilt,
                         int coloniesBuilt,
                         int citiesBuilt
) {
}
