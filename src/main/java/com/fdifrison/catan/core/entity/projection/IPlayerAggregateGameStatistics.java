package com.fdifrison.catan.core.entity.projection;

public interface IPlayerAggregateGameStatistics {
    Long getId();

    Long getDevelopcarddrawn();

    Long getKnightcardplayed();

    Long getRoadsbuilt();

    Long getColoniesbuilt();

    Long getCitiesbuilt();

    Boolean getLongestRoad();

    Boolean getLargestArmy();
}
