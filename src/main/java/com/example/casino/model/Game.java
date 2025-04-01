package com.example.casino.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "status", length = 255)
    private String status;

    @Column(name = "pot")
    private Integer pot;

    @Column(name = "combination", length = 255)
    private String combination;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public Game() {}

    // Getters and Setters
    public Long getGameId() { return gameId; }
    public void setGameId(Long gameId) { this.gameId = gameId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getPot() { return pot; }
    public void setPot(Integer pot) { this.pot = pot; }

    public String getCombination() { return combination; }
    public void setCombination(String combination) { this.combination = combination; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
