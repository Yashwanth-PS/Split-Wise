package com.splitwise.service;

import com.splitwise.dto.TransactionDTO;
import com.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    // Ideal way to call a method in userService that calls the userRepository to get the Users
    @Autowired
    UserRepository userRepository;
    @Override
    public List<TransactionDTO> settleUp() {
        return null;
    }
}
