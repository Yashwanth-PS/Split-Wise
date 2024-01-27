package com.splitwise.service;

import com.splitwise.model.Expense;
import com.splitwise.model.Group;
import com.splitwise.repository.ExpenseRepository;
import com.splitwise.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupServiceImplTest {

    private static final String TEST_GROUP_NAME = "testGroup";
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private ExpenseRepository expenseRepository;
    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    public void givenNonExistingGroup_whenSettleUp_thenEmptyList() {
        // Given
        when(groupRepository.findByName(TEST_GROUP_NAME)).thenReturn(Optional.empty());

        // When-Then
        assertEquals(Collections.emptyList(), groupService.settleUp(TEST_GROUP_NAME));
    }

    @Test
    public void givenExistingGroupWithNoExpenses_whenSettleUp_thenEmptyList() {
        // Given
        Group group = initializeGroup();
        when(groupRepository.findByName(TEST_GROUP_NAME)).thenReturn(Optional.of(group));
        when(expenseRepository.findByGroup(group)).thenReturn(Collections.emptyList());

        // When-Then
        assertEquals(Collections.emptyList(), groupService.settleUp(TEST_GROUP_NAME));
    }

    @Test
    public void givenExistingGroupWithExpenses_whenSettleUp_thenEmptyList() {
        // Given
        Group group = initializeGroup();
        Expense expense = initializeExpense();

        when(groupRepository.findByName(TEST_GROUP_NAME)).thenReturn(Optional.of(group));
        when(expenseRepository.findByGroup(group)).thenReturn(Collections.singletonList(expense));

        // When-Then
        assertEquals(Collections.emptyList(), groupService.settleUp(TEST_GROUP_NAME));
    }

    // Helper method to initialize an Group with dummy data
    private Group initializeGroup() {
        Group group = new Group();
        group.setName(TEST_GROUP_NAME);
        return group;
    }

    // Helper method to initialize an expense with dummy data
    private Expense initializeExpense() {
        Expense expense = new Expense();
        expense.setUserExpenses(Collections.emptyList());
        expense.setId(1L);
        expense.setAmount(100.0);
        return expense;
    }
}