package com.splitwise.controller;

import com.splitwise.dto.GetUserResponseDTO;
import com.splitwise.dto.RegisterUserRequestDTO;
import com.splitwise.dto.RegisterUserResponseDTO;
import com.splitwise.model.User;
import com.splitwise.model.constant.ResponseStatus;
import com.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/* 5 steps to enhance the Spring Boot application:
- Include the Spring Boot Starter Web dependency from the Maven Repository.
- Update the project's POM file and refresh Maven to download the necessary dependencies.
- Modify the controller to a RestController, enabling it to handle HTTP POST and GET requests.
- Update methods with @GetMapping or @PostMapping annotations. For example, use @PostMapping
  for registering a user and @GetMapping for retrieving user data.
- Understand how to retrieve data. For HTTP requests, you can utilize Request DTOs
  (Data Transfer Objects). In HTTP, data can be sent either in the message body or as headers.
  Use the @RequestBody annotation to handle data sent in the message body. */

// @Controller

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/register/") // If we are sending the data in path then @RequestParam will be used
    public RegisterUserResponseDTO registerUser(@RequestBody() RegisterUserRequestDTO requestDTO) {
        RegisterUserResponseDTO responseDTO = new RegisterUserResponseDTO();
        try {
            User user = new User();
            user.setName(requestDTO.getUserName());
            user.setPhoneNumber(requestDTO.getPhone());
            user.setPassword(requestDTO.getPassword());
            User savedUser = userService.registerUser(user);
            responseDTO.setUserId(savedUser.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("Successfully Created the User");
        } catch (Exception ex) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(ex.getMessage());
        }
        return responseDTO;
    }

    /* @PostMapping("/user/get/")
    // public GetUserResponseDTO getUser(GetUserRequestDTO requestDTO) { // Before using the Post Request
    public GetUserResponseDTO getUser(@RequestBody GetUserRequestDTO requestDto) {
        GetUserResponseDTO responseDTO = new GetUserResponseDTO();
        try {
            User user = userService.getUser(requestDto.getUserId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("User Retrieved Successfully");
            responseDTO.setUserName(user.getName());
            responseDTO.setUserId(user.getId());
            responseDTO.setPhone(user.getPhoneNumber());
        } catch (Exception ex) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(ex.getMessage());
        }
        return responseDTO;
    } */

    @GetMapping("/user/get/") // Using the Get Mapping and Request Param
    public GetUserResponseDTO getUser(@RequestParam() Long userId) {
        GetUserResponseDTO responseDTO = new GetUserResponseDTO();
        try {
            User user = userService.getUser(userId);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("User Retrieved Successfully!!!");
            responseDTO.setUserName(user.getName());
            responseDTO.setUserId(user.getId());
            responseDTO.setPhone(user.getPhoneNumber());
        } catch (Exception ex) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(ex.getMessage());
        }
        return responseDTO;
    }
}