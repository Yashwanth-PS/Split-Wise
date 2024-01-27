package com.splitwise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity(name = "SPLITWISE_GROUP")
public class Group extends BaseModel {
    private String name;
    private String description;
    @ManyToMany(mappedBy = "groups")
    private List<User> users;
    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
    private double totalAmountSpend;
}

/*    A B C D
G1 -> A B C D
G2 -> A B C
G3 -> A B    */
