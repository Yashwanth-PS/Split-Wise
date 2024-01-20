package com.splitwise.model;

import com.splitwise.model.constant.UserExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "SPLITWISE_EXPENSEUSER")
public class UserExpense extends BaseModel{
    @ManyToOne
    private User user;
    private double amount;
    @Enumerated(EnumType.ORDINAL)
    private UserExpenseType userExpenseType;
}

/* User - UserExpense - 1:M -> Uni-Directional
   Expense - UserExpense - 1:M -> Uni-Directional

Expense:- For each Expense we want to know User Expense
   Paid : A = 100, B = 100
   Has To Pay : A = 50, B = 50, C = 100 */