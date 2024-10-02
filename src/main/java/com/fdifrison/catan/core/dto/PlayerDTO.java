package com.fdifrison.catan.core.dto;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlayerDTO(
        @JsonProperty(access = READ_ONLY) long id, String username, String email, String avatarUrl, boolean deleted) {}
