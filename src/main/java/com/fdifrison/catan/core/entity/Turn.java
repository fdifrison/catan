package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "turn")
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turn_seq")
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @NotNull @Column(name = "player_color")
    private String playerColor;

    @CreationTimestamp
    @Column(name = "start_timestamp", nullable = false)
    private Instant startTimestamp;

    @Max(12)
    @Column(name = "outcome", nullable = false)
    private int outcome;

    @NotNull @Min(0)
    @Column(name = "develop_card_drawn", nullable = false)
    private int developCardDrawn;

    @NotNull @Column(name = "knight_card_played", nullable = false)
    private boolean knightCardPlayed = false;

    @NotNull @Column(name = "longest_road", nullable = false)
    private boolean longestRoad = false;

    @NotNull @Column(name = "largest_army", nullable = false)
    private boolean largestArmy = false;

    @NotNull @Min(0)
    @Column(name = "roads_built", nullable = false)
    private int roadsBuilt;

    @NotNull @Min(0)
    @Column(name = "colonies_built", nullable = false)
    private int coloniesBuilt;

    @NotNull @Min(0)
    @Column(name = "cities_built", nullable = false)
    private int citiesBuilt;

    public long getId() {
        return id;
    }

    public Turn setId(long id) {
        this.id = id;
        return this;
    }

    public Game getGame() {
        return game;
    }

    public Turn setGame(Game game) {
        this.game = game;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public Turn setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public @NotNull String getPlayerColor() {
        return playerColor;
    }

    public Turn setPlayerColor(@NotNull String playerColor) {
        this.playerColor = playerColor;
        return this;
    }

    public Instant getStartTimestamp() {
        return startTimestamp;
    }

    public Turn setStartTimestamp(Instant startTimestamp) {
        this.startTimestamp = startTimestamp;
        return this;
    }

    @Max(12)
    public int getOutcome() {
        return outcome;
    }

    public Turn setOutcome(@Max(12) int outcome) {
        this.outcome = outcome;
        return this;
    }

    @NotNull @Min(0)
    public int getDevelopCardDrawn() {
        return developCardDrawn;
    }

    public Turn setDevelopCardDrawn(@NotNull @Min(0) int developCardDrawn) {
        this.developCardDrawn = developCardDrawn;
        return this;
    }

    @NotNull public boolean isKnightCardPlayed() {
        return knightCardPlayed;
    }

    public Turn setKnightCardPlayed(@NotNull boolean knightCardPlayed) {
        this.knightCardPlayed = knightCardPlayed;
        return this;
    }

    @NotNull public boolean isLongestRoad() {
        return longestRoad;
    }

    public Turn setLongestRoad(@NotNull boolean longestRoad) {
        this.longestRoad = longestRoad;
        return this;
    }

    @NotNull public boolean isLargestArmy() {
        return largestArmy;
    }

    public Turn setLargestArmy(@NotNull boolean largestArmy) {
        this.largestArmy = largestArmy;
        return this;
    }

    @NotNull @Min(0)
    public int getRoadsBuilt() {
        return roadsBuilt;
    }

    public Turn setRoadsBuilt(@NotNull @Min(0) int roadsBuilt) {
        this.roadsBuilt = roadsBuilt;
        return this;
    }

    @NotNull @Min(0)
    public int getColoniesBuilt() {
        return coloniesBuilt;
    }

    public Turn setColoniesBuilt(@NotNull @Min(0) int coloniesBuilt) {
        this.coloniesBuilt = coloniesBuilt;
        return this;
    }

    @NotNull @Min(0)
    public int getCitiesBuilt() {
        return citiesBuilt;
    }

    public Turn setCitiesBuilt(@NotNull @Min(0) int citiesBuilt) {
        this.citiesBuilt = citiesBuilt;
        return this;
    }
}
