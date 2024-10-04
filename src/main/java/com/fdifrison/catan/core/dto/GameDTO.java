package com.fdifrison.catan.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdifrison.catan.core.entity.Game;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Schema(description = "Representation of a game and its players")
public record GameDTO(
        @JsonProperty(access = READ_ONLY)
        long id,
        @NotNull String gameName,
        @NotNull Game.GameType gameType,
        int turnNumber,
        @Min(8) int requiredVictoryPoints,
        @NotNull Instant startTimestamp,
        @Nullable Instant endTimestamp,
        @NotNull List<GamePlayerDTO> gamePlayers) {

    @Schema(description = "Player state relative to a specific game")
    public record GamePlayerDTO(

            long playerId,
            @NotNull String username,
            @NotNull String email,
            String avatarUrl,
            int startOrder,
            @NotNull String playerColor,

            @Min(0) int developCardDrawn,
            @Min(0) int knightCardPlayed,
            boolean longestRoad,
            boolean largestArmy,
            @Min(2) int roadsBuilt,
            @Min(2) int coloniesBuilt,
            @Min(0) int citiesBuilt,

            @Min(0) int plainScore,
            int victoryPointsDrawn,
            boolean winner

    ) {

    }
}
