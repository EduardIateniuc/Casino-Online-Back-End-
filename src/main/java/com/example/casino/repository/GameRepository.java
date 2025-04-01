package com.example.casino.repository;

import com.example.casino.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByStatus(String status);
    List<Game> findGameByPlayerId(Long playerId); // Queries Game where player.id = playerId
}