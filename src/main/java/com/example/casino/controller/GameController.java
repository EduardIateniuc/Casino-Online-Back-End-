package com.example.casino.controller;

import com.example.casino.dto.request.CreateGameRequest;
import com.example.casino.dto.response.GameResponse;
import com.example.casino.dto.response.GameStats;
import com.example.casino.model.Game;
import com.example.casino.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"https://casino-online-r7dr-eduardiateniucs-projects.vercel.app", "http://localhost:5173"})
@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@RequestBody CreateGameRequest request) {
        Game game = gameService.createGame(request);
        return ResponseEntity.ok(new GameResponse(game));
    }

    @MessageMapping("/join-room")
    @SendTo("/topic/room")
    public String joinRoom(String username) {
        return username + " joined the room!";
    }

    @GetMapping(value = "/getGames", produces = "application/json")
    public ResponseEntity<List<GameResponse>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        List<GameResponse> gameResponses = games.stream()
                .map(GameResponse::new)
                .collect(Collectors.toList());
        System.out.println("Games: " + gameResponses);
        return ResponseEntity.ok(gameResponses);
    }

    @GetMapping(value = "/getGames/{telegramId}", produces = "application/json")
    public ResponseEntity<List<GameResponse>> getGame(@PathVariable Long telegramId) {
        List<Game> games = gameService.findGamesByTelegramId(telegramId);
        List<GameResponse> gameResponses = games.stream()
                .map(GameResponse::new)
                .collect(Collectors.toList());
        System.out.println("Games for telegramId " + telegramId + ": " + gameResponses);
        return ResponseEntity.ok(gameResponses);
    }

    @GetMapping(value = "/getGamesRating/{telegramId}", produces = "application/json")
    public ResponseEntity<GameStats> getGameRating(@PathVariable Long telegramId) {
        List<Game> games = gameService.findGamesByTelegramId(telegramId);
        GameStats gameStats = new GameStats();
        int winCount = 0;
        int lossCount = 0;
        for (Game game : games) {
            if ("WIN".equals(game.getStatus())) winCount += 1;
            else if ("LOSE".equals(game.getStatus())) lossCount += 1;
        }
        gameStats.setTotalGames(games.size());
        gameStats.setWins(winCount);
        gameStats.setLosses(lossCount);
        Double winRate = (double) winCount / games.size() * 100;
        gameStats.setWinRate(winRate);
        return ResponseEntity.ok(gameStats);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<GameResponse>> searchGames(@RequestParam("status") String status) {
        List<GameResponse> games = gameService.findByStatus(status).stream()
                .map(GameResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(games);
    }

    @GetMapping(value = "/test", produces = "application/json")
    public ResponseEntity<GameStats> testGameStats() {
        GameStats gameStats = new GameStats();
        gameStats.setWins(5);
        gameStats.setLosses(3);
        gameStats.setTotalGames(8);
        gameStats.setWinRate(0.625);
        return ResponseEntity.ok(gameStats);
    }
}