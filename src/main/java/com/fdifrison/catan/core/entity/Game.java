package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NotNull @Column(name = "name")
    private String name;

    @NotNull @Column(name = "number_of_players")
    private int numberOfPlayers;

    @NotNull @Column(name = "required_victory_points")
    private int requiredVictoryPoints;

    @CreationTimestamp
    @Column(name = "start_timestamp")
    private Instant startTimestamp;

    @Column(name = "end_timestamp")
    private Instant endTimestamp;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turn> turns = new ArrayList<>();

    public long getId() {
        return id;
    }

    public Game setId(long id) {
        this.id = id;
        return this;
    }

    public @NotNull String getName() {
        return name;
    }

    public Game setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    @NotNull public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Game setNumberOfPlayers(@NotNull int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
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

    public List<Turn> getTurns() {
        return turns;
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
}
