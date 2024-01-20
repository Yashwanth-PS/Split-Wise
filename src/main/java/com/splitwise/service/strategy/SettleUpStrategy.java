package com.splitwise.service.strategy;

import com.splitwise.dto.TransactionDTO;
import com.splitwise.model.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<TransactionDTO> settleUp(List<Expense> expenses);
}