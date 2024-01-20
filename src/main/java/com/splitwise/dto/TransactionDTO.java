package com.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // No-Args or Default Constructor
@AllArgsConstructor // Parameterised Constructor
public class TransactionDTO {
    private String fromUserName;
    private String toUserName;
    private double amount;

    @Override
    public String toString() {
        return fromUserName + " should pay " + amount + " to " + toUserName + "\n";
    }
}