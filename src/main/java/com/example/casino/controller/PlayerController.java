package com.example.casino.controller;

import com.example.casino.dto.request.AmountRequest;
import com.example.casino.dto.response.PlayerBalance;
import com.example.casino.services.PlayerService;
import com.example.casino.dto.response.UserProfileResponse;
import com.example.casino.dto.request.TelegramLoginRequest;
import com.example.casino.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "https://casino-online-r7dr-eduardiateniucs-projects.vercel.app")
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PatchMapping(value = "/{playerId}/balance", produces = "application/json")
    public ResponseEntity<PlayerBalance> updateBalance(@PathVariable Long playerId, @RequestBody AmountRequest amountRequest) {
        Player updatedPlayer = playerService.updatePlayerBalance(playerId, amountRequest.getAmount());
        PlayerBalance response = new PlayerBalance();
        response.setBalance(updatedPlayer.getBalance());
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{playerId}/balance", produces = "application/json")
    public ResponseEntity<PlayerBalance> getPlayerBalance(@PathVariable Long playerId) {
        Optional<Player> player = playerService.findByTelegramId(playerId);

        if (player.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Player p = player.get();
        PlayerBalance playerBalance = new PlayerBalance();
        playerBalance.setBalance(p.getBalance());

        return ResponseEntity.ok(playerBalance);
    }

    @GetMapping(value = "/getPlayersRating", produces = "application/json")
    public ResponseEntity<List<UserProfileResponse>> getPlayersRating() {
        List<UserProfileResponse> players = playerService.getPlayersRating();
        return ResponseEntity.ok(players);
    }

    @PostMapping("/telegram-login")
    public ResponseEntity<UserProfileResponse> telegramLogin(@RequestBody TelegramLoginRequest request) {
        Player player = playerService.registerOrUpdatePlayer(request);

        UserProfileResponse response = new UserProfileResponse();
        response.setId(player.getId());
        response.setTelegramId(player.getTelegramId());
        response.setUsername(player.getUsername());
        response.setFirstName(player.getFirstName());
        response.setLastName(player.getLastName());
        response.setPhotoUrl(player.getPhotoUrl());
        response.setBalance(player.getBalance());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserProfileResponse> getPlayerById(@PathVariable Long id) {
        Optional<Player> player = playerService.findByTelegramId(id);

        if (player.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Player p = player.get();
        UserProfileResponse response = new UserProfileResponse();
        response.setId(p.getId());
        response.setTelegramId(p.getTelegramId());
        response.setUsername(p.getUsername());
        response.setFirstName(p.getFirstName());
        response.setLastName(p.getLastName());
        response.setPhotoUrl(p.getPhotoUrl());
        response.setBalance(p.getBalance());

        return ResponseEntity.ok(response);
    }
}