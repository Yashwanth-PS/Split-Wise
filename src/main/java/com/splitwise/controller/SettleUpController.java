package com.splitwise.controller;

import com.splitwise.dto.TransactionDTO;
import com.splitwise.service.GroupService;
import com.splitwise.service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/settleup/v1")
public class SettleUpController {

    @Autowired
    private InitService initService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/init") // Get Call --> www.domain_name.com/hello, localhost:8080/hello
    public ResponseEntity<String> initialise() { // Response Entity --> Internally converts Object into JSON
        try {
            initService.initialise();
            return ResponseEntity.ok("DONE"); // HTTP Status is 200
        } catch (Exception exception) {
            log.error("Initialization failed.", exception); // Logging the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Initialization failed.");
        }
    }

    @GetMapping("/settle")
    public ResponseEntity<List<TransactionDTO>> settleUp() { // Response Entity --> Internally converts Object into JSON
        try {
            List<TransactionDTO> transactionDTOS = groupService.settleUp();
            return ResponseEntity.ok(transactionDTOS); // HTTP Status is 200
        } catch (Exception exception) {
            log.error("Settlement failed.", exception); // Logging the exception
            return ResponseEntity.notFound().build();
        }
    }
}