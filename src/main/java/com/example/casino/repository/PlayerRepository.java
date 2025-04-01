package com.example.casino.repository;

import com.example.casino.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByTelegramId(Long telegramId);
    List<Player> findAllByOrderByBalanceDesc();
}