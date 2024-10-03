package com.fdifrison.catan.core.dto;

public class PlayerOrderDTO implements Comparable<PlayerOrderDTO> {

    int order;
    long playerId;
    String playerColor;

    public PlayerOrderDTO(int order, long playerId, String playerColor) {
        this.order = order;
        this.playerId = playerId;
        this.playerColor = playerColor;
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

    public String getPlayerColor() {
        return playerColor;
    }

    public PlayerOrderDTO setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
        return this;
    }
}
