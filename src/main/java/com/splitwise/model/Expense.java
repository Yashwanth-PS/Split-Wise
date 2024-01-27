package com.splitwise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.splitwise.model.constant.Currency;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity(name = "SPLITWISE_EXPENSE")
public class Expense extends BaseModel {
    private String description;
    private double amount;
    @OneToMany(mappedBy = "expense", fetch = FetchType.LAZY)
    private List<UserExpense> UserExpenses;
    @Enumerated(EnumType.STRING) // One expense will have one currency
    private Currency currency;
    @ManyToOne // To establish the many-to-one relationship
    private Group group;
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