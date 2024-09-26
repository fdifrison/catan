package com.fdifrison.catan.core.dto;

import java.util.Map;

public class PlayerScoreMapDTO {

    private Map<Long, PlayerScoreDTO> playerScoreMap;

    public Map<Long, PlayerScoreDTO> getPlayerScoreMap() {
        return playerScoreMap;
    }

    public PlayerScoreMapDTO setPlayerScoreMap(Map<Long, PlayerScoreDTO> playerScoreMap) {
        this.playerScoreMap = playerScoreMap;
        return this;
    }
}
