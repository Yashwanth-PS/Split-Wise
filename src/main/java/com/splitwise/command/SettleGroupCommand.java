package com.splitwise.command;

import com.splitwise.controller.GroupController;
import com.splitwise.dto.SettleGroupRequestDTO;
import com.splitwise.dto.SettleGroupResponseDTO;
import com.splitwise.dto.TransactionDTO;
import com.splitwise.model.constant.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// SettleGroup(groupId) will have two parameters in it
// First it will create the Object of Group Controller
// Then it will create the Object of Settle Group Command

@Slf4j
@Component
public class SettleGroupCommand implements Command {

    private final GroupController groupController;

    @Autowired
    // @Repository, @Service, @RestController, @Component - When the Application starts Spring will create a Bean
    public SettleGroupCommand(GroupController groupController) { // The Group Controller Bean would be Injected here by Spring Dependency Injection
        this.groupController = groupController;
    }

    @Override
    public boolean canExecute(String input) {
        if (!input.startsWith("settleGroup") || (input.split(" ").length != 2)) {
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
        // Which Object do we create here?
        // We will create settleGroupRequestDTO and call GroupController
        // By passing the requestDTO to the Controller
        String[] arr = input.split(" ");
        long groupId = Long.parseLong(arr[1]);
        SettleGroupRequestDTO requestDTO = new SettleGroupRequestDTO();
        requestDTO.setGroupId(groupId);
        SettleGroupResponseDTO responseDTO = groupController.settleGroup(requestDTO);
        if (responseDTO.getResponseStatus() == ResponseStatus.SUCCESS) {
            List<TransactionDTO> transactions = responseDTO.getTransactions();
            for (TransactionDTO transaction : transactions) {
                log.info(String.valueOf(transaction));
            }
        } else {
            log.info("Group Settlement Failed " + responseDTO.getMessage());
        }
    }
}