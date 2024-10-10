package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "turn_view")
@IdClass(GamePlayerId.class)
public class TurnView {

    @Id
    @Column(name = "game_id")
    private long gameId;

    @Id
    @Column(name = "player_id")
    private long playerId;

    @Column(name = "develop_card_drawn")
    private long developCardDrawn;

    @Column(name = "knight_card_played")
    private long knightCardPlayed;

    @Column(name = "roads_built")
    private long roadsBuilt;

    @Column(name = "colonies_built")
    private long coloniesBuilt;

    @Column(name = "cities_built")
    private long citiesBuilt;

    @Column(name = "last_turn_when_longest_road")
    private long lastTurnLongestRoad;

    @Column(name = "last_turn_when_largest_army")
    private long lastTurnLargestArmy;

    public long getGameId() {
        return gameId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public long getDevelopCardDrawn() {
        return developCardDrawn;
    }

    public long getKnightCardPlayed() {
        return knightCardPlayed;
    }

    public long getRoadsBuilt() {
        return roadsBuilt;
    }

    public long getColoniesBuilt() {
        return coloniesBuilt;
    }

    public long getCitiesBuilt() {
        return citiesBuilt;
    }

    public long getLastTurnLongestRoad() {
        return lastTurnLongestRoad;
    }

    public long getLastTurnLargestArmy() {
        return lastTurnLargestArmy;
    }
}
