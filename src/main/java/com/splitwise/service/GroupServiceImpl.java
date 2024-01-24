package com.splitwise.service;

import com.splitwise.dto.TransactionDTO;
import com.splitwise.model.Group;
import com.splitwise.repository.GroupRepository;
import com.splitwise.service.strategy.HeapBasedSettleUpStrategy;
import com.splitwise.service.strategy.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{
    // Ideal way to call a method in userService that calls the userRepository to get the Users
    final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<TransactionDTO> settleUp(String groupName) {
        Optional<Group> group = groupRepository.findByName(groupName);
        SettleUpStrategy settleUpStrategy = new HeapBasedSettleUpStrategy();
        return settleUpStrategy.settleUp(group.get().getExpenses());
    }
}
