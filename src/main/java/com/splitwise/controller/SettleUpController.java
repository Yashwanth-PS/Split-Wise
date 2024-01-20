package com.splitwise.controller;

import com.splitwise.dto.TransactionDTO;
import com.splitwise.service.GroupService;
import com.splitwise.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SettleUpController {

    @Autowired
    private InitService initService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/init") // Get Call --> www.domain_name.com/hello, localhost:8080/hello
    public ResponseEntity initialise() { // Response Entity --> Internally converts Object into JSON
        initService.initialise();
        return ResponseEntity.ok("DONE"); // HTTP Status is 200
    }

    @GetMapping("/settleup")
    public ResponseEntity settleUp() { // Response Entity --> Internally converts Object into JSON
        List<TransactionDTO> transactionDTOS = groupService.settleUp();
        return ResponseEntity.ok(transactionDTOS); // HTTP Status is 200
    }
}
