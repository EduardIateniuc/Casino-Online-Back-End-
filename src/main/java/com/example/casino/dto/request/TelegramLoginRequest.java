package com.example.casino.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramLoginRequest {
    private Long telegramId;
    private String username;
    private String firstName;
    private String lastName;
    private String photoUrl;

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

    public String getPhotoUrl() {
        return photoUrl;
    }
}
