package com.example.casino.services;

import com.example.casino.dto.request.CreateGameRequest;
import com.example.casino.model.Game;
import com.example.casino.model.Player;
import com.example.casino.repository.GameRepository;
import com.example.casino.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Game createGame(CreateGameRequest request) {
        Game game = new Game();
        game.setStatus(request.getStatus());
        game.setPot(request.getPot());
        game.setCombination(request.getCombination());

        Player player = playerRepository.findByTelegramId(request.getTelegramId());
        if (player == null) {
            throw new IllegalArgumentException("Player with telegramId " + request.getTelegramId() + " not found");
        }
        game.setPlayer(player);

        return gameRepository.save(game);
    }

    public List<Game> findGamesByTelegramId(Long telegramId) {
        Player player = playerRepository.findByTelegramId(telegramId);
        if (player == null) {
            return Collections.emptyList(); // Return empty list if player doesn't exist
        }
        Long playerId = player.getId();
        List<Game> games = gameRepository.findGameByPlayerId(playerId);
        return games != null ? games : Collections.emptyList(); // Return empty list if no games found
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> findByStatus(String status) {
        return gameRepository.findByStatus(status);
    }
}