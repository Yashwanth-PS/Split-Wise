package com.splitwise.command;

import com.splitwise.controller.UserController;
import com.splitwise.dto.GetUserRequestDTO;
import com.splitwise.dto.GetUserResponseDTO;
import com.splitwise.model.constant.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j // This is a Lombok Annotation which will generate a Logger for this class
@Component // Get User Command will have User Controller --> GetUser using userId
public class GetUserCommand implements Command {
    private UserController userController;

    @Autowired
    // @Repository, @Service, @RestController, @Component - When the Application starts Spring will create a Bean
    public GetUserCommand(UserController userController) { // The User Controller Bean would be Injected here by Spring Dependency Injection
        this.userController = userController;
    }

    @Override
    public boolean canExecute(String input) {
        if (!input.startsWith("getUser") || input.split(" ").length != 2) {
            return false;
        }
        String[] arr = input.split(" ");
        try {
            long groupId = Long.parseLong(arr[1]);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    @Override
    public void execute(String input) {
        String[] arr = input.split(" ");
        long userId = Long.parseLong(arr[1]);
        GetUserRequestDTO requestDTO = new GetUserRequestDTO();
        requestDTO.setUserId(userId);
        // GetUserResponseDTO responseDto = userController.getUser(requestDTO); // Before using the Get Mapping and RequestParam
        GetUserResponseDTO responseDTO = userController.getUser(userId);
        if (responseDTO.getResponseStatus() == ResponseStatus.SUCCESS) {
            log.info("User Id = " + responseDTO.getUserId());
            log.info("User name = " + responseDTO.getUserName());
            log.info("User phone = " + responseDTO.getPhone());
        } else {
            log.info("getUser  failed  with message= " + responseDTO.getMessage());
        }
    }
}