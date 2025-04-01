package com.example.casino.dto.response;

import lombok.Data;

@Data
public class PlayerBalance {
    private Integer balance;

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBalance() {
        return balance;
    }
}
