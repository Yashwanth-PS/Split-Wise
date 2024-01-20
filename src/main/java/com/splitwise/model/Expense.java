package com.splitwise.model;

import com.splitwise.model.constant.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "SPLITWISE_EXPENSE")
public class Expense extends BaseModel{
    private String description;
    private double amount;
    @OneToMany
    private List<UserExpense> UserExpenses;
    @Enumerated(EnumType.ORDINAL) // One expense will have one currency
    private Currency currency;
}

/* Expense : UserExpense -> 1 : M {Uni-Directional on Expense side}
   Group - Expense
     1   -    M
     1   -    1
   Group : Expense -> 1 : M {Uni-Directional on Group side}
{
    "description" : "Dinner",
    "amount" : 1000,
    "group" : 1,
    "currency" : "INR",
    "paid" : [{"Yash" : 500}, {"Abhi" : 500}],
    "hastToPay" : [{"Yash" : 250}, {"Abhi" : 250}, {"Prajwal" : 250}, {"Sachin" : 250}]
} */