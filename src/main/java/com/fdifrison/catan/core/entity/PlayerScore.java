package com.fdifrison.catan.core.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class PlayerScore {

    private long playerId;

    private int startOrder;

    private Boolean longestRoad;

    private Boolean largestArmy;

    private int score;

    private int victoryPoints;

    public long getPlayerId() {
        return playerId;
    }

    public PlayerScore setPlayerId(long playerId) {
        this.playerId = playerId;
        return this;
    }

    public int getStartOrder() {
        return startOrder;
    }

    public PlayerScore setStartOrder(int startOrder) {
        this.startOrder = startOrder;
        return this;
    }

    public Boolean getLongestRoad() {
        return longestRoad;
    }

    public PlayerScore setLongestRoad(Boolean longestRoad) {
        this.longestRoad = longestRoad;
        return this;
    }

    public Boolean getLargestArmy() {
        return largestArmy;
    }

    public PlayerScore setLargestArmy(Boolean largestArmy) {
        this.largestArmy = largestArmy;
        return this;
    }

    public int getScore() {
        return score;
    }

    public PlayerScore setScore(int score) {
        this.score = score;
        return this;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public PlayerScore setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
        return this;
    }
}
