package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @CreationTimestamp
    @Column(name = "start_timestamp", nullable = false)
    private Instant startTimestamp;

    @UpdateTimestamp
    @Column(name = "end_timestamp")
    private Instant endTimestamp;

    @ElementCollection
    @CollectionTable(name = "player_score", joinColumns = @JoinColumn(name = "game_id"))
    @OrderBy("startOrder ASC")
    private List<PlayerScore> playerScores = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turn> turns = new ArrayList<>();

    public long getId() {
        return id;
    }

    public Game setId(long id) {
        this.id = id;
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

    public List<PlayerScore> getPlayerScores() {
        return playerScores;
    }

    public Game setPlayerScores(List<PlayerScore> playerScores) {
        this.playerScores = playerScores;
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
