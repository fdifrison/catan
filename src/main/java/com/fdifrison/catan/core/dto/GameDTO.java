package com.fdifrison.catan.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public class GameDTO {

    private Integer id;

    @NotNull @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant startTimestamp;

    private Instant endTimestamp;

    public Instant getEndTimestamp() {
        return endTimestamp;
    }

    public GameDTO setEndTimestamp(Instant endTimestamp) {
        this.endTimestamp = endTimestamp;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Instant getStartTimestamp() {
        return startTimestamp;
    }

    public GameDTO setStartTimestamp(Instant startTimestamp) {
        this.startTimestamp = startTimestamp;
        return this;
    }
}
