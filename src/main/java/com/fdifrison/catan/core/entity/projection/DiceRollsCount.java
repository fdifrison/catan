package com.fdifrison.catan.core.entity.projection;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DiceRollsCount(@NotNull @Min(2) @Max(12) int outcome, @NotNull long count) {
}
