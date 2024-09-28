package com.fdifrison.catan.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull @Column(name = "name", nullable = false)
    private String name;

    @NotNull @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turn> turns = new ArrayList<>();

    public Player addTurn(Turn turn) {
        this.turns.add(turn);
        turn.setPlayer(this);
        return this;
    }

    public void removeTurn(Turn turn) {
        this.turns.remove(turn);
        turn.setPlayer(null);
    }

    public long getId() {
        return id;
    }

    public Player setId(long id) {
        this.id = id;
        return this;
    }

    public @NotNull String getName() {
        return name;
    }

    public Player setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public Player setEmail(@NotNull String email) {
        this.email = email;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Player setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
