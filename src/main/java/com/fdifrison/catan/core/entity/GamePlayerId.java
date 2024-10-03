package com.fdifrison.catan.core.entity;

import java.io.Serializable;
import java.util.Objects;

public class GamePlayerId implements Serializable {

    private long gameId;

    private long playerId;

    public GamePlayerId() {}

    public GamePlayerId(long gameId, long playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }

    public long getGameId() {
        return gameId;
    }

    public GamePlayerId setGameId(long gameId) {
        this.gameId = gameId;
        return this;
    }

    public long getPlayerId() {
        return playerId;
    }

    public GamePlayerId setPlayerId(long playerId) {
        this.playerId = playerId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePlayerId that = (GamePlayerId) o;
        return gameId == that.gameId && playerId == that.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId);
    }
}
