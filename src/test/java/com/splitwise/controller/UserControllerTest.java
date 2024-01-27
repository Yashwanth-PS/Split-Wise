package com.splitwise.controller;

import com.splitwise.dto.GetUserResponseDTO;
import com.splitwise.dto.RegisterUserRequestDTO;
import com.splitwise.dto.RegisterUserResponseDTO;
import com.splitwise.mapper.UserMapper;
import com.splitwise.model.User;
import com.splitwise.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserController userController;

    @Test
    public void givenValidUserWhenRegisterUserThenRegistrationSucceeds() {
        // Arrange
        RegisterUserRequestDTO requestDTO = createSampleRegisterUserRequestDTO();
        User expectedUser = UserMapper.mapRequestDTOToUser(requestDTO);
        when(userServiceImpl.registerUser(any())).thenReturn(expectedUser);

        // Act
        ResponseEntity<RegisterUserResponseDTO> response = userController.registerUser(requestDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        RegisterUserResponseDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(expectedUser.getName(), responseBody.getUserName());
        assertEquals(expectedUser.getPhoneNumber(), responseBody.getPhone());
    }

    @Test
    void givenUserWhenRegisterUserThenRegisterUserFailure() {
        // Arrange
        RegisterUserRequestDTO requestDTO = createSampleRegisterUserRequestDTO();
        when(userServiceImpl.registerUser(any())).thenThrow(new RuntimeException("Registration failed"));

        // Act
        ResponseEntity<RegisterUserResponseDTO> response = userController.registerUser(requestDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void givenValidUserWhenGettingUserThenSuccessfullyGettingUser() throws Exception {
        // Arrange
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        when(userServiceImpl.getUser(userId)).thenReturn(expectedUser);

        // Act
        ResponseEntity<GetUserResponseDTO> response = userController.getUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        GetUserResponseDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(expectedUser.getId(), responseBody.getUserId());
    }

    @Test
    void givenInValidUserWhenGettingUserThenGettingUserFailure() throws Exception {
        // Arrange
        Long userId = 1L;
        when(userServiceImpl.getUser(userId)).thenThrow(new RuntimeException("User Not Found"));

        // Act
        ResponseEntity<GetUserResponseDTO> response = userController.getUser(userId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Helper method to create a sample RegisterUserRequestDTO
    private RegisterUserRequestDTO createSampleRegisterUserRequestDTO() {
        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO();
        requestDTO.setUserName("JohnDoe");
        requestDTO.setEmail("john@gmail.com");
        requestDTO.setPhone("9876543210");
        requestDTO.setPassword("secretpassword");
        return requestDTO;
    }
}