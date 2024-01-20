package com.splitwise.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// No @Entity because no table for this model since we are using it for entering the transaction
public class Transaction{
    private  String from;
    private  String to;
    private  int amount;

    @Override
    public String toString() {
        return from + " should pay " + amount + " to " + to + "\n";
    }
}