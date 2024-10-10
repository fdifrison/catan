package com.fdifrison.catan.core.dto;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdifrison.catan.core.entity.Game;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "Required info to setup a new game")
public record GameSetupDTO(@NotNull GameSetupDTO.SetupDTO gameInfo, @Min(2) List<GamePlayerInfoDTO> playersInfo) {

    @Schema(description = "Basic info about the game")
    public record SetupDTO(
            @NotNull String gameName, @NotNull Game.GameType gameType, @Min(8) int requiredVictoryPoints) {}

    @Schema(description = "Basic info about the players participating to the game")
    public record GamePlayerInfoDTO(
            @JsonProperty(access = READ_ONLY) long gameId,
            long playerId,
            @Min(0) @Max(6) int startOrder,
            @NotNull String playerColor)
            implements Comparable<GamePlayerInfoDTO> {

        @Override
        public int compareTo(GamePlayerInfoDTO o) {
            return Integer.compare(startOrder, o.startOrder());
        }
    }
}
