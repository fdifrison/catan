package com.fdifrison.catan.core.dto;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Player Entity not related to a specific game")
public record PlayerDTO(
        @JsonProperty(access = READ_ONLY) long id,
        @NotNull String username,
        @NotNull String email,
        @Nullable String avatarUrl,
        boolean deleted) {}
