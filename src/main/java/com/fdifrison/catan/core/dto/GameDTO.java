package com.fdifrison.catan.core.dto;

import com.fdifrison.catan.core.entity.PlayerScore;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GameDTO {

    private Integer id;

    private Instant startTimestamp;

    private Instant endTimestamp;

    private List<PlayerScore> playerScores = new ArrayList<>();

    public Instant getEndTimestamp() {
        return endTimestamp;
    }

    public GameDTO setEndTimestamp(Instant endTimestamp) {
        this.endTimestamp = endTimestamp;
        return this;
    }

    public @NotNull Integer getId() {
        return id;
    }

    public GameDTO setId(@NotNull Integer id) {
        this.id = id;
        return this;
    }

    public List<PlayerScore> getPlayerScores() {
        return playerScores;
    }

    public GameDTO setPlayerScores(List<PlayerScore> playerScores) {
        this.playerScores = playerScores;
        return this;
    }

    public @NotNull Instant getStartTimestamp() {
        return startTimestamp;
    }

    public GameDTO setStartTimestamp(@NotNull Instant startTimestamp) {
        this.startTimestamp = startTimestamp;
        return this;
    }
}
