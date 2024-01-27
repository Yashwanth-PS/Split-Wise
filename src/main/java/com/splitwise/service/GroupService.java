package com.splitwise.service;

import com.splitwise.dto.TransactionDTO;

import java.util.List;

public interface GroupService {
    List<TransactionDTO> settleUp(String groupName);
}