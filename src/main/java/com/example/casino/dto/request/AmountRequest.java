package com.example.casino.dto.request;

import lombok.Data;

@Data
public class AmountRequest {
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
