package com.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "SPLITWISE_USER")
@Builder
public class User extends BaseModel{
    private String name;
    private String email;
    private String phoneNumber;
    @ManyToMany
    private List<Group> groups;
    private String password;
}

/* User - Group : M:M -> Bidirectional
   User - UserExpense : 1:M

Expense:
   Paid : A = 100, B = 100
   - We want to know how much each user has spent or has to spend
   - We want to know the amount and user from each userExpense Object
   Has To Pay : A = 50, B = 50, C = 100 */