package com.fdifrison.catan.core.entity.projection;

public record PlayerAggregateGameStatistics(
        int developCardDrawn,
        int knightCardPlayed,
        int roadsBuilt,
        int coloniesBuilt,
        int citiesBuilt,
        boolean longestRoad,
        boolean largestArmy
) {
}
