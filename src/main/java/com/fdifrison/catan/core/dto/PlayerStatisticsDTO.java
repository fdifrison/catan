package com.fdifrison.catan.core.dto;

import java.util.Collection;
import java.util.Set;

public record PlayerStatisticsDTO(long playerId, DiceStatisticsDTO statisticsDTO) {
    public record DiceStatisticsDTO(Set<Long> diceNumber, Collection<Double> numberOfTime) {}
}
