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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turn_id_gen")
    @SequenceGenerator(name = "turn_id_gen", sequenceName = "turn_id_seq", allocationSize = 25)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @CreationTimestamp
    @Column(name = "roll_timestamp", nullable = false)
    private Instant rollTimestamp;

    @NotNull @Min(2)
    @Max(12)
    @Column(name = "outcome", nullable = false)
    private Short outcome;

    @NotNull @Min(0)
    @Column(name = "develop_card_drawn", nullable = false)
    private Short developCardDrawn;

    @NotNull @Column(name = "develop_cart_played", nullable = false)
    private Boolean developCartPlayed = false;

    @NotNull @Min(0)
    @Column(name = "roads_built", nullable = false)
    private Short roadsBuilt;

    @NotNull @Min(0)
    @Column(name = "colonies_built", nullable = false)
    private Short coloniesBuilt;

    @NotNull @Min(0)
    @Column(name = "cities_built", nullable = false)
    private Short citiesBuilt;

    public long getId() {
        return id;
    }

    public Turn setId(long id) {
        this.id = id;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public Turn setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public Game getGame() {
        return game;
    }

    public Turn setGame(Game game) {
        this.game = game;
        return this;
    }

    public Instant getRollTimestamp() {
        return rollTimestamp;
    }

    public Turn setRollTimestamp(Instant rollTimestamp) {
        this.rollTimestamp = rollTimestamp;
        return this;
    }

    public @NotNull @Min(2) @Max(12) Short getOutcome() {
        return outcome;
    }

    public Turn setOutcome(@NotNull @Min(2) @Max(12) Short outcome) {
        this.outcome = outcome;
        return this;
    }

    public @NotNull @Min(0) Short getDevelopCardDrawn() {
        return developCardDrawn;
    }

    public Turn setDevelopCardDrawn(@NotNull @Min(0) Short developCardDrawn) {
        this.developCardDrawn = developCardDrawn;
        return this;
    }

    public @NotNull Boolean getDevelopCartPlayed() {
        return developCartPlayed;
    }

    public Turn setDevelopCartPlayed(@NotNull Boolean developCartPlayed) {
        this.developCartPlayed = developCartPlayed;
        return this;
    }

    public @NotNull @Min(0) Short getRoadsBuilt() {
        return roadsBuilt;
    }

    public Turn setRoadsBuilt(@NotNull @Min(0) Short roadsBuilt) {
        this.roadsBuilt = roadsBuilt;
        return this;
    }

    public @NotNull @Min(0) Short getColoniesBuilt() {
        return coloniesBuilt;
    }

    public Turn setColoniesBuilt(@NotNull @Min(0) Short coloniesBuilt) {
        this.coloniesBuilt = coloniesBuilt;
        return this;
    }

    public @NotNull @Min(0) Short getCitiesBuilt() {
        return citiesBuilt;
    }

    public Turn setCitiesBuilt(@NotNull @Min(0) Short citiesBuilt) {
        this.citiesBuilt = citiesBuilt;
        return this;
    }
}
