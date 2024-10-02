package com.fdifrison.catan.core.dto;

import java.util.List;

public record GameSetupDTO(String name, int requiredVictoryPoints, List<PlayerOrderDTO> players) {}
