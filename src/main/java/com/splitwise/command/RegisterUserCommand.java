package com.splitwise.command;

import com.splitwise.controller.UserController;
import com.splitwise.dto.RegisterUserRequestDTO;
import com.splitwise.dto.RegisterUserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component // Register User Command will have Controller --> Register User - name, phone, password
public class RegisterUserCommand implements Command {
    private final UserController userController;
    @Autowired // @Repository, @Service, @RestController, @Component - When the Application starts Spring will create a Bean
    public RegisterUserCommand(UserController userController) { // The User Controller Bean would be Injected here by Spring Dependency Injection
        this.userController = userController;
    }

    @Override
    public boolean canExecute(String input) {
        return input.startsWith("registerUser") && input.split(" ").length == 4;
    }

    @Override
    public void execute(String input) {
        String[] arr = input.split(" ");
        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO();
        requestDTO.setUserName(arr[1]);
        requestDTO.setPhone(arr[2]);
        requestDTO.setPassword(arr[3]);

        ResponseEntity<RegisterUserResponseDTO> responseDTO = userController.registerUser(requestDTO);

        if (responseDTO.getStatusCode() == org.springframework.http.HttpStatus.OK) {
            log.info("User Created with Id: " + responseDTO.getBody().getUserName());
        } else {
            log.error("Failed to Register the User");
        }
    }
}