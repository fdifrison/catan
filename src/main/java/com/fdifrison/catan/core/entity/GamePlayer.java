package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "game_player")
public class GamePlayer {
    @SequenceGenerator(name = "game_player_id_gen", sequenceName = "game_id_seq", allocationSize = 25)
    @EmbeddedId
    private GamePlayerId id;

    @MapsId("playerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @MapsId("gameId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "longest_road", nullable = false)
    private Boolean longestRoad = false;

    @Column(name = "largest_army", nullable = false)
    private Boolean largestArmy = false;

    @Column(name = "score", nullable = false)
    private Short score;

    @Column(name = "victory_points", nullable = false)
    private Short victoryPoints;

    @Column(name = "ranking", nullable = false)
    private Short ranking;

    @OneToMany(mappedBy = "gamePlayer")
    private Set<Roll> rolls = new LinkedHashSet<>();

    public GamePlayerId getId() {
        return id;
    }

    public void setId(GamePlayerId id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Boolean getLongestRoad() {
        return longestRoad;
    }

    public void setLongestRoad(Boolean longestRoad) {
        this.longestRoad = longestRoad;
    }

    public Boolean getLargestArmy() {
        return largestArmy;
    }

    public void setLargestArmy(Boolean largestArmy) {
        this.largestArmy = largestArmy;
    }

    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    public Short getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(Short victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public Short getRanking() {
        return ranking;
    }

    public void setRanking(Short ranking) {
        this.ranking = ranking;
    }

    public Set<Roll> getRolls() {
        return rolls;
    }

    public void setRolls(Set<Roll> rolls) {
        this.rolls = rolls;
    }

}