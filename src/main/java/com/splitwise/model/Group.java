package com.Project.Splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "SPLITWISE_GROUP")
public class Group extends BaseModel{
    private String name;
    private String description;
    @ManyToMany(mappedBy = "groups")
    private List<User> users;
    @OneToMany
    private List<Expense> expenses;
    private double totalAmountSpend;
}

/*    A B C D
G1 -> A B C D
G2 -> A B C
G3 -> A B    */
