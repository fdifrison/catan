package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;

@Entity
@IdClass(PlayerRankingId.class)
@Table(name = "player_ranking")
public class PlayerRanking {

    @Id
    @Column(name = "game_id")
    private long gameId;

    @Id
    @Column(name = "player_id")
    private long playerId;

    @Column(name = "plain_score")
    private int plainScore;

    @Column(name = "victory_points_drawn")
    private int victoryPointsDrawn;

    @Column(name = "winner")
    private boolean winner;

    public long getGameId() {
        return gameId;
    }

    public PlayerRanking setGameId(long gameId) {
        this.gameId = gameId;
        return this;
    }

    public long getPlayerId() {
        return playerId;
    }

    public PlayerRanking setPlayerId(long playerId) {
        this.playerId = playerId;
        return this;
    }

    public int getPlainScore() {
        return plainScore;
    }

    public PlayerRanking setPlainScore(int plainScore) {
        this.plainScore = plainScore;
        return this;
    }

    public int getVictoryPointsDrawn() {
        return victoryPointsDrawn;
    }

    public PlayerRanking setVictoryPointsDrawn(int victoryPointsDrawn) {
        this.victoryPointsDrawn = victoryPointsDrawn;
        return this;
    }

    public boolean isWinner() {
        return winner;
    }

    public PlayerRanking setWinner(boolean winner) {
        this.winner = winner;
        return this;
    }
}
