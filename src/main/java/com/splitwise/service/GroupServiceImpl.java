package com.splitwise.service;

import com.splitwise.dto.TransactionDTO;
import com.splitwise.model.Expense;
import com.splitwise.model.Group;
import com.splitwise.repository.ExpenseRepository;
import com.splitwise.repository.GroupRepository;
import com.splitwise.service.strategy.HeapBasedSettleUpStrategy;
import com.splitwise.service.strategy.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{
    // Ideal way to call a method in userService that calls the userRepository to get the Users
    final GroupRepository groupRepository;
    final ExpenseRepository expenseRepository;

    public GroupServiceImpl(GroupRepository groupRepository, ExpenseRepository expenseRepository) {
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<TransactionDTO> settleUp(String groupName) {
        Optional<Group> optionalGroup = groupRepository.findByName(groupName);

        if (optionalGroup.isEmpty()) { // Handle the case where the group with the given name doesn't exist
            // throw an exception, return an error response, etc.
            return Collections.emptyList(); // For now, let's return an empty list of transactions
        }

        Group group = optionalGroup.get();

        // Fetch all expenses associated with the group
        List<Expense> expenses = expenseRepository.findByGroup(group);

        // Use the SettleUpStrategy to calculate transactions
        SettleUpStrategy settleUpStrategy = new HeapBasedSettleUpStrategy();
        List<TransactionDTO> transactions = settleUpStrategy.settleUp(expenses);

        return transactions;
    }
}
