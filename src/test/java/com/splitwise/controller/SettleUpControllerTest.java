package com.splitwise.controller;

import com.splitwise.dto.TransactionDTO;
import com.splitwise.service.GroupService;
import com.splitwise.service.InitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SettleUpControllerTest {

    private static final String GROUP_NAME = "Group1";
    private static final String INITIALIZATION_FAILED_MESSAGE = "Initialization failed.";
    private static final String INITIALIZATION_SUCCESS_MESSAGE = "DONE";
    private static final String NON_EXISTING_GROUP_NAME = "NonExistingGroup";
    @Mock
    private InitService initService;

    @Mock
    private GroupService groupService;

    @InjectMocks
    private SettleUpController settleUpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidInitialization_whenInitialise_thenShouldReturnDone() {
        // Given
        doNothing().when(initService).initialise();

        // When
        ResponseEntity<String> response = settleUpController.initialise();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(INITIALIZATION_SUCCESS_MESSAGE, response.getBody());
        verify(initService, times(1)).initialise();
    }

    @Test
    void givenFailedInitialization_whenInitialise_thenShouldReturnInternalServerError() {
        // Given
        doThrow(new RuntimeException(INITIALIZATION_FAILED_MESSAGE)).when(initService).initialise();

        // When
        ResponseEntity<String> response = settleUpController.initialise();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(INITIALIZATION_FAILED_MESSAGE, response.getBody());
        verify(initService, times(1)).initialise();
    }

    @Test
    void givenValidGroup_whenSettleUp_thenShouldReturnTransactions() {
        // Given
        List<TransactionDTO> expectedTransactions = Collections.singletonList(new TransactionDTO());
        when(groupService.settleUp(GROUP_NAME)).thenReturn(expectedTransactions);

        // When
        ResponseEntity<List<TransactionDTO>> response = settleUpController.settleUp(GROUP_NAME);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTransactions, response.getBody());
        verify(groupService, times(1)).settleUp(GROUP_NAME);
    }

    @Test
    void givenNonExistingGroup_whenSettleUp_thenShouldReturnNotFound() {
        // Given
        when(groupService.settleUp(NON_EXISTING_GROUP_NAME)).thenThrow(new RuntimeException("Settlement failed."));

        // When
        ResponseEntity<List<TransactionDTO>> response = settleUpController.settleUp(NON_EXISTING_GROUP_NAME);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(groupService, times(1)).settleUp(NON_EXISTING_GROUP_NAME);
    }
}