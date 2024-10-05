package com.fdifrison.catan.core.entity.projection;

public interface GamePlayerStatistics {
    Long getId();

    Long getDevelopCardDrawn();

    Long getKnightCardPlayed();

    Long getRoadsBuilt();

    Long getColoniesBuilt();

    Long getCitiesBuilt();

    Boolean getLongestRoad();

    Boolean getLargestArmy();
}
