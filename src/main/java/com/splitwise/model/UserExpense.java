package com.splitwise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.splitwise.model.constant.UserExpenseType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity(name = "SPLITWISE_EXPENSEUSER")
public class UserExpense extends BaseModel {
    @ManyToOne
    private User user;
    private double amount;
    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;
    @ManyToOne(fetch = FetchType.LAZY) // To establish the many-to-one relationship
    @JoinColumn(name = "expense_id")
    private Expense expense;
}

/* User - UserExpense - 1:M -> Uni-Directional
   Expense - UserExpense - 1:M -> Uni-Directional

Expense:- For each Expense we want to know User Expense
   Paid : A = 100, B = 100
   Has To Pay : A = 50, B = 50, C = 100 */