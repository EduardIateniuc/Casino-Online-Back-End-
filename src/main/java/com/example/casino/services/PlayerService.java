package com.example.casino.services;
import com.example.casino.dto.response.UserProfileResponse;
import com.example.casino.model.Player;
import com.example.casino.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.casino.dto.request.TelegramLoginRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public Player saveOrUpdatePlayer(Player player) {
        return playerRepository.save(player);
    }


    @Transactional
    public Player updatePlayerBalance(Long telegramId, Integer amount) {
        Player player = playerRepository.findByTelegramId(telegramId);

        player.setBalance(amount);

        return playerRepository.save(player);
    }

    public List<UserProfileResponse> getPlayersRating() {
        List<Player> players = playerRepository.findAllByOrderByBalanceDesc();
        return players.stream()
                .limit(10) // Limit to top 10
                .map(player -> new UserProfileResponse(
                        player.getId(),
                        player.getTelegramId(),
                        player.getUsername(),
                        player.getFirstName(),
                        player.getLastName(),
                        player.getBalance(),
                        player.getPhotoUrl()
                ))
                .collect(Collectors.toList());
    }



    public Optional<Player> findByTelegramId(Long telegramId) {
        return Optional.ofNullable(playerRepository.findByTelegramId(telegramId));
    }

    public Player registerOrUpdatePlayer(TelegramLoginRequest request) {
        Optional<Player> existingPlayer = Optional.ofNullable(playerRepository.findByTelegramId(request.getTelegramId()));

        Player player = existingPlayer.orElseGet(() -> new Player());
        player.setTelegramId(request.getTelegramId());
        player.setUsername(request.getUsername());
        player.setFirstName(request.getFirstName());
        player.setLastName(request.getLastName());
        player.setPhotoUrl(request.getPhotoUrl());

        if (!existingPlayer.isPresent()) {
            player.setBalance(1000);
        }

        return playerRepository.save(player);
    }
}