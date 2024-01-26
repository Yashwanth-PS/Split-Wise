package com.splitwise.controller;

import com.splitwise.dto.GetUserResponseDTO;
import com.splitwise.dto.RegisterUserRequestDTO;
import com.splitwise.dto.RegisterUserResponseDTO;
import com.splitwise.model.User;
import com.splitwise.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.splitwise.mapper.UserMapper.mapRequestDTOToUser;
import static com.splitwise.mapper.UserMapper.mapRequestUserToDTO;
import static com.splitwise.mapper.UserMapper.mapUserToUserResponseDTO;

@Slf4j
@RestController
@RequestMapping("/user/v1")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register") // If we are sending the data in path then @RequestParam will be used
    public ResponseEntity<RegisterUserResponseDTO> registerUser(@RequestBody() RegisterUserRequestDTO requestDTO) {
        RegisterUserResponseDTO responseDTO;
        try {
            User user = mapRequestDTOToUser(requestDTO);
            User savedUser = userServiceImpl.registerUser(user);
            responseDTO = mapRequestUserToDTO(savedUser);
        } catch (Exception ex) {
            log.error("Failed to Register the User: " + ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(responseDTO);
    }

// POST: http://localhost:8080/user/v1/register
/* {
    "userName": "JohnDoe",
    "email": "john@gmail.com",
    "phone": "9876543210",
    "password": "secretpassword"
} */

    @GetMapping("/get/{userId}") // Using the Get Mapping and Request Param
    public ResponseEntity<GetUserResponseDTO> getUser(@PathVariable Long userId) {
        GetUserResponseDTO responseDTO;
        try {
            User user = userServiceImpl.getUser(userId);
            responseDTO = mapUserToUserResponseDTO(user);
        } catch (Exception ex) {
            log.error("Failed to Get the User: " + ex.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(responseDTO);
    }

} // GET: http://localhost:8080/user/v1/get/1