package com.example.casino.dto.response;

import com.example.casino.model.Game;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GameResponse {
    private Long gameId;
    private String combination;
    private LocalDateTime createdAt;
    private Long playerId;
    private Integer pot;
    private String status;

    public GameResponse(Game game) {
        this.gameId = game.getGameId();
        this.combination = game.getCombination();
        this.createdAt = game.getCreatedAt();
        this.playerId = game.getPlayer().getId();
        this.pot = game.getPot();
        this.status = game.getStatus();
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public void setPot(Integer pot) {
        this.pot = pot;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getCombination() {
        return combination;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Integer getPot() {
        return pot;
    }

    public String getStatus() {
        return status;
    }
}
