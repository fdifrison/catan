package com.fdifrison.catan.core.entity;

import java.io.Serializable;
import java.util.Objects;

public class PlayerRankingId implements Serializable {

    private long gameId;

    private long playerId;

    public PlayerRankingId(long gameId, long playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerRankingId that = (PlayerRankingId) o;
        return gameId == that.gameId && playerId == that.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId);
    }
}
