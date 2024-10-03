package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;

@Entity
@IdClass(GamePlayerId.class)
@Table(name = "game_player")
public class GamePlayer {

    @Id
    @Column(name = "game_id")
    private long gameId;

    @Id
    @Column(name = "player_id")
    private long playerId;

    @Column(name = "start_order")
    private int startOrder;

    @Column(name = "player_color")
    private String playerColor;

    @Column(name = "plain_score")
    private int plainScore;

    @Column(name = "victory_points_drawn")
    private int victoryPointsDrawn;

    @Column(name = "winner")
    private boolean winner;

    public long getGameId() {
        return gameId;
    }

    public GamePlayer setGameId(long gameId) {
        this.gameId = gameId;
        return this;
    }

    public long getPlayerId() {
        return playerId;
    }

    public GamePlayer setPlayerId(long playerId) {
        this.playerId = playerId;
        return this;
    }

    public int getStartOrder() {
        return startOrder;
    }

    public GamePlayer setStartOrder(int startOrder) {
        this.startOrder = startOrder;
        return this;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public GamePlayer setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
        return this;
    }

    public int getPlainScore() {
        return plainScore;
    }

    public GamePlayer setPlainScore(int plainScore) {
        this.plainScore = plainScore;
        return this;
    }

    public int getVictoryPointsDrawn() {
        return victoryPointsDrawn;
    }

    public GamePlayer setVictoryPointsDrawn(int victoryPointsDrawn) {
        this.victoryPointsDrawn = victoryPointsDrawn;
        return this;
    }

    public boolean isWinner() {
        return winner;
    }

    public GamePlayer setWinner(boolean winner) {
        this.winner = winner;
        return this;
    }
}
