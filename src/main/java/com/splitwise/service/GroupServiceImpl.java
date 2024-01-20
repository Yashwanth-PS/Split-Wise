package com.splitwise.service;

import com.splitwise.dto.TransactionDTO;
import com.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    // Ideal way to call a method in userService that calls the userRepository to get the Users
    final UserRepository userRepository;

    public GroupServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<TransactionDTO> settleUp() {
        return null;
    }
}
