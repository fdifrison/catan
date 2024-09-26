package com.fdifrison.catan.core.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PlayerScoreDTO(
        @NotNull long playerId,
        @NotNull @Min(0) int startOrder,
        boolean longestRoad,
        boolean largestArmy,
        @Min(0) int score,
        @Min(0) int victoryPoints) {

    public PlayerScoreDTO(long playerId, int startOrder) {
        this(playerId, startOrder, false, false, 0, 0);
    }

    @Override
    public String toString() {
        return "PlayerScoreDTO{" + "playerId="
                + playerId + ", startOrder="
                + startOrder + ", longestRoad="
                + longestRoad + ", largestArmy="
                + largestArmy + ", score="
                + score + ", victoryPoints="
                + victoryPoints + '}';
    }
}
