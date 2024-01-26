package com.splitwise.service;

import com.splitwise.repository.ExpenseRepository;
import com.splitwise.repository.GroupRepository;
import com.splitwise.repository.UserExpenseRepository;
import com.splitwise.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InitServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserExpenseRepository userExpenseRepository;

    @InjectMocks
    private InitServiceImpl initService;

    @Test
    void givenValidDataWhenInitialiseThenRepositoriesShouldBeCalled() {
        // Execute the method to be tested
        initService.initialise();

        // Verify that repository save methods were called
        verify(userRepository, times(1)).saveAll(any());
        verify(groupRepository, times(2)).save(any());
        verify(expenseRepository, times(1)).saveAll(any());
        verify(userExpenseRepository, times(4)).saveAll(any());
    }

    @Test
    void givenExceptionInUserRepositorySaveWhenInitialiseThenExceptionShouldBeThrown() {
        // Simulate an exception during user repository save
        when(userRepository.saveAll(any())).thenThrow(new RuntimeException("Simulated repository exception"));

        // Execute the method to be tested
        assertThrows(RuntimeException.class, () -> initService.initialise());
    }
}