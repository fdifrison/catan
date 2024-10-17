package com.fdifrison.catan.core.dto;

public class GamePlayerStatisticsDTO {
    private long playerId;
    private long developCardDrawn;
    private long knightCardPlayed;
    private long roadsBuilt;
    private long coloniesBuilt;
    private long citiesBuilt;
    private boolean longestRoad = false;
    private boolean largestArmy = false;

    public long getPlayerId() {
        return playerId;
    }

    public GamePlayerStatisticsDTO setPlayerId(long playerId) {
        this.playerId = playerId;
        return this;
    }

    public long getDevelopCardDrawn() {
        return developCardDrawn;
    }

    public GamePlayerStatisticsDTO setDevelopCardDrawn(long developCardDrawn) {
        this.developCardDrawn = developCardDrawn;
        return this;
    }

    public long getKnightCardPlayed() {
        return knightCardPlayed;
    }

    public GamePlayerStatisticsDTO setKnightCardPlayed(long knightCardPlayed) {
        this.knightCardPlayed = knightCardPlayed;
        return this;
    }

    public long getRoadsBuilt() {
        return roadsBuilt;
    }

    public GamePlayerStatisticsDTO setRoadsBuilt(long roadsBuilt) {
        this.roadsBuilt = roadsBuilt;
        return this;
    }

    public long getColoniesBuilt() {
        return coloniesBuilt;
    }

    public GamePlayerStatisticsDTO setColoniesBuilt(long coloniesBuilt) {
        this.coloniesBuilt = coloniesBuilt;
        return this;
    }

    public long getCitiesBuilt() {
        return citiesBuilt;
    }

    public GamePlayerStatisticsDTO setCitiesBuilt(long citiesBuilt) {
        this.citiesBuilt = citiesBuilt;
        return this;
    }

    public boolean isLongestRoad() {
        return longestRoad;
    }

    public GamePlayerStatisticsDTO setLongestRoad(boolean longestRoad) {
        this.longestRoad = longestRoad;
        return this;
    }

    public boolean isLargestArmy() {
        return largestArmy;
    }

    public GamePlayerStatisticsDTO setLargestArmy(boolean largestArmy) {
        this.largestArmy = largestArmy;
        return this;
    }
}
