package com.example.casino.model;

import java.util.HashSet;
import java.util.Set;

public class PokerRoom {
    private String id;
    private Set<String> players = new HashSet<>();

    public PokerRoom(String id) {
        this.id = id;
    }

    public void addPlayer(String player) {
        players.add(player);
    }

    public void removePlayer(String player) {
        players.remove(player);
    }

    public Set<String> getPlayers() {
        return players;
    }

    public String getId() {
        return id;
    }
}
