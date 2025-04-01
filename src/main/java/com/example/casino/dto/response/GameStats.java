package com.example.casino.dto.response;

import lombok.Data;

@Data
public class GameStats {
    private Integer wins;
    private Integer losses;
    private Integer totalGames;
    private Double winRate;


    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    public void setWinRate(Double winRate) {
        this.winRate = winRate;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public Integer getTotalGames() {
        return totalGames;
    }

    public Double getWinRate() {
        return winRate;
    }
}
