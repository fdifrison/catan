package com.fdifrison.catan.core.dto;

public class PlayerOrderDTO implements Comparable<PlayerOrderDTO> {

    int order;
    long playerId;

    public PlayerOrderDTO(int order, long playerId) {
        this.order = order;
        this.playerId = playerId;
    }

    @Override
    public int compareTo(PlayerOrderDTO o) {
        return Integer.compare(order, o.order);
    }

    public int getOrder() {
        return order;
    }

    public PlayerOrderDTO setOrder(int order) {
        this.order = order;
        return this;
    }

    public long getPlayerId() {
        return playerId;
    }

    public PlayerOrderDTO setPlayerId(long playerId) {
        this.playerId = playerId;
        return this;
    }
}
