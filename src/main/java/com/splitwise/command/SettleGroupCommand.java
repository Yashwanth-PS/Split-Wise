package com.splitwise.command;

import com.splitwise.controller.SettleUpController;
import com.splitwise.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

// SettleGroup(groupId) will have two parameters in it
// First it will create the Object of Group Controller
// Then it will create the Object of Settle Group Command

@Slf4j
@Component
public class SettleGroupCommand implements Command {

    private final SettleUpController settleUpController;

    @Autowired
    // @Repository, @Service, @RestController, @Component - When the Application starts Spring will create a Bean
    public SettleGroupCommand(SettleUpController settleUpController) { // The Group Controller Bean would be Injected here by Spring Dependency Injection
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean canExecute(String input) {
        return input.startsWith("settleGroup");
    }

    @Override
    public void execute(String input) {
        String[] arr = input.split(" ");
        String groupName = arr[1];
        ResponseEntity<List<TransactionDTO>> responseEntity = settleUpController.settleUp(groupName);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<TransactionDTO> transactions = responseEntity.getBody();
            if (transactions != null) {
                transactions.forEach(transaction -> log.info(String.valueOf(transaction)));
            } else {
                log.info("No transactions found");
            }
        } else {
            log.error("Group Settlement Failed with status: " + responseEntity.getStatusCodeValue());
        }
    }
}