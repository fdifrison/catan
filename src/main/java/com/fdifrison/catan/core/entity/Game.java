package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "game")
public class Game {

    public enum GameType {
        STANDARD,
        SEAFARERS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NotNull @Column(name = "game_name")
    private String gameName;

    @Enumerated(EnumType.STRING)
    @NotNull @Column(name = "game_type")
    private GameType gameType;

    @NotNull @Column(name = "required_victory_points")
    private int requiredVictoryPoints;

    @CreationTimestamp
    @Column(name = "start_timestamp")
    private Instant startTimestamp;

    @Column(name = "end_timestamp")
    private Instant endTimestamp;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turn> turns = new ArrayList<>();

    @OneToMany(mappedBy = "gameId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GamePlayer> gamePlayers = new ArrayList<>();

    public long getId() {
        return id;
    }

    public Game setId(long id) {
        this.id = id;
        return this;
    }

    public @NotNull String getGameName() {
        return gameName;
    }

    public Game setGameName(@NotNull String gameName) {
        this.gameName = gameName;
        return this;
    }

    public @NotNull GameType getGameType() {
        return gameType;
    }

    public Game setGameType(@NotNull GameType gameType) {
        this.gameType = gameType;
        return this;
    }

    @NotNull public int getRequiredVictoryPoints() {
        return requiredVictoryPoints;
    }

    public Game setRequiredVictoryPoints(@NotNull int requiredVictoryPoints) {
        this.requiredVictoryPoints = requiredVictoryPoints;
        return this;
    }

    public Instant getStartTimestamp() {
        return startTimestamp;
    }

    public Game setStartTimestamp(Instant startTimestamp) {
        this.startTimestamp = startTimestamp;
        return this;
    }

    public Instant getEndTimestamp() {
        return endTimestamp;
    }

    public Game setEndTimestamp(Instant endTimestamp) {
        this.endTimestamp = endTimestamp;
        return this;
    }

    public Game addTurn(Turn turn) {
        turns.add(turn);
        turn.setGame(this);
        return this;
    }

    public void removeTurn(Turn turn) {
        turns.remove(turn);
        turn.setGame(null);
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public Game setTurns(List<Turn> turns) {
        this.turns = turns;
        return this;
    }

    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public Game setGamePlayers(List<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
        return this;
    }
}
