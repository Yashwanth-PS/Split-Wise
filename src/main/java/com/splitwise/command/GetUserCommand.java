package com.splitwise.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Get User Command will have User Controller --> GetUser using userId
public class GetUserCommand implements Command {
    private UserController userController;

    @Autowired
    // @Repository, @Service, @RestController, @Component - When the Application starts Spring will create a Bean
    public GetUserCommand(UserController userController) { // what ever  object  created in the  groupcontroller will be passed over  here  by spring  Dependency injection( topoogical sort
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
        GetUserRequestDto requestDto = new GetUserRequestDto();
        requestDto.setUserId(userId);
//        GetUserResponseDto responseDto = userController.getUser(requestDto);// before  using the  get mapping and  requstparam
        GetUserResponseDto responseDto = userController.getUser(userId);
        if (responseDto.getResponseStatus() == ResponseStatus.SUCCESS) {
            System.out.println("User Id = " + responseDto.getUserId());
            System.out.println("User name = " + responseDto.getUserName());
            System.out.println("User phone= " + responseDto.getPhone());
        } else {
            System.out.println("getUser  failed  with message= " + responseDto.getMessage());
        }
    }
}