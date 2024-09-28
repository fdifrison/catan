package com.fdifrison.catan.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record PlayerDTO(@NotEmpty String name, @NotEmpty @Email String email, String avatarUrl, boolean deleted) {
}
