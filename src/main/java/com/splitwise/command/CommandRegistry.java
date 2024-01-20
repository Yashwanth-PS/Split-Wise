package com.splitwise.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Inorder to make all the commands available here, we can add the collection of all the commands

@Component
public class CommandRegistry {
    private List<Command> registeredCommand = new ArrayList<>();

    /* @Autowired
    private GetUserCommand getUserCommand; // It is also possible to autowire the Individual Component */

    @Autowired
    public CommandRegistry(GetUserCommand getUserCommand, RegisterUserCommand registerUserCommand, SettleGroupCommand settleGroupCommand) {
        registeredCommand.add(getUserCommand);
        registeredCommand.add(registerUserCommand);
        registeredCommand.add(settleGroupCommand);
    }

    public void process(String input) {
        for (Command registeredCommand : registeredCommand) {
            if (registeredCommand.canExecute(input)) {
                registeredCommand.execute(input);
                break;
            }
        }
    }
}