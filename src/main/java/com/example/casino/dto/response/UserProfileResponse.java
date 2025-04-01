package com.example.casino.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileResponse {
    private Long id;
    private Long telegramId;
    private String username;
    private String firstName;
    private String lastName;
    private Integer balance;
    private String photoUrl;

    public UserProfileResponse() {
    }

    public UserProfileResponse(Long id, Long telegramId, String username, String firstName, String lastName, Integer balance, String photoUrl) {
        this.id = id;
        this.telegramId = telegramId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.photoUrl = photoUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
