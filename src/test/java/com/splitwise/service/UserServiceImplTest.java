package com.splitwise.service;

import com.splitwise.model.User;
import com.splitwise.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private Long USER_ID = 1L;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void whenGetUserById_thenReturnUser() throws Exception {
        // Arrange
        User expectedUser = createSampleExpectedUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(expectedUser));

        // Act
        User actualUser = userServiceImpl.getUser(USER_ID);

        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void whenGetUserByIdNotFound_thenThrowException() {
        // Arrange
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(Exception.class, () -> userServiceImpl.getUser(USER_ID));
    }

    @Test
    void whenRegisterUser_thenSaveAndReturnUser() {
        // Arrange
        User userToSave = createSampleUserToSave();
        User savedUser = createSampleExpectedUser();
        User expectedUser = createSampleExpectedUser();
        when(userRepository.save(userToSave)).thenReturn(savedUser);

        // Act
        User actualUser = userServiceImpl.registerUser(userToSave);

        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    // Helper methods
    private User createSampleExpectedUser() {
        User expectedUser = new User();
        expectedUser.setId(USER_ID);
        expectedUser.setName("JohnDoe");
        return expectedUser;
    }

    private User createSampleUserToSave() {
        User expectedUser = new User();
        expectedUser.setName("JohnDoe");
        return expectedUser;
    }
}