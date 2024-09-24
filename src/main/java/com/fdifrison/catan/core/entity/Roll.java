package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;
import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "roll")
public class Roll {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roll_id_gen")
    @SequenceGenerator(name = "roll_id_gen", sequenceName = "roll_id_seq", allocationSize = 25)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private GamePlayer gamePlayer;

    @CreationTimestamp
    @Column(name = "roll_timestamp", nullable = false)
    private Instant rollTimestamp;

    @Column(name = "outcome", nullable = false)
    private Short outcome;

    @Column(name = "develop_card_drawn", nullable = false)
    private Short developCardDrawn;

    @Column(name = "develop_cart_played", nullable = false)
    private Boolean developCartPlayed = false;

    @Column(name = "roads_built", nullable = false)
    private Short roadsBuilt;

    @Column(name = "colonies_built", nullable = false)
    private Short coloniesBuilt;

    @Column(name = "cities_built", nullable = false)
    private Short citiesBuilt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public Instant getRollTimestamp() {
        return rollTimestamp;
    }

    public void setRollTimestamp(Instant rollTimestamp) {
        this.rollTimestamp = rollTimestamp;
    }

    public Short getOutcome() {
        return outcome;
    }

    public void setOutcome(Short outcome) {
        this.outcome = outcome;
    }

    public Short getDevelopCardDrawn() {
        return developCardDrawn;
    }

    public void setDevelopCardDrawn(Short developCardDrawn) {
        this.developCardDrawn = developCardDrawn;
    }

    public Boolean getDevelopCartPlayed() {
        return developCartPlayed;
    }

    public void setDevelopCartPlayed(Boolean developCartPlayed) {
        this.developCartPlayed = developCartPlayed;
    }

    public Short getRoadsBuilt() {
        return roadsBuilt;
    }

    public void setRoadsBuilt(Short roadsBuilt) {
        this.roadsBuilt = roadsBuilt;
    }

    public Short getColoniesBuilt() {
        return coloniesBuilt;
    }

    public void setColoniesBuilt(Short coloniesBuilt) {
        this.coloniesBuilt = coloniesBuilt;
    }

    public Short getCitiesBuilt() {
        return citiesBuilt;
    }

    public void setCitiesBuilt(Short citiesBuilt) {
        this.citiesBuilt = citiesBuilt;
    }
}
