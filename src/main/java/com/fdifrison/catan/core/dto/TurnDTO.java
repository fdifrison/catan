package com.fdifrison.catan.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Schema(description = "Action performed by a specific player in a specific game")
public record TurnDTO(
        long gameId,
        long playerId,
        @Min(2) @Max(12) int outcome,
        @Min(0) int developCardDrawn,
        boolean knightCardPlayed,
        boolean longestRoad,
        boolean largestArmy,
        @Min(0) int roadsBuilt,
        @Min(0) int coloniesBuilt,
        @Min(0) int citiesBuilt) {}
