package com.fdifrison.catan.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.util.Objects;
import org.hibernate.Hibernate;

@Embeddable
public class GamePlayerId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 9156114010975060176L;

    @Column(name = "player_id", nullable = false)
    private Integer playerId;

    @Column(name = "game_id", nullable = false)
    private Integer gameId;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GamePlayerId entity = (GamePlayerId) o;
        return Objects.equals(this.gameId, entity.gameId) && Objects.equals(this.playerId, entity.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId);
    }
}
