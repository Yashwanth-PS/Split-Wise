package com.splitwise.service.strategy;

import com.splitwise.dto.TransactionDTO;
import com.splitwise.model.Expense;
import com.splitwise.model.User;
import com.splitwise.model.UserExpense;
import com.splitwise.model.constant.UserExpenseType;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class HeapBasedSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<TransactionDTO> settleUp(List<Expense> expenses) {
        HashMap<User, Double> userOutstandingMap = new HashMap<>();
        List<TransactionDTO> transactionList = new ArrayList<>();
        // The for loop below calculates the outstanding amount for each user in the group [All users in list of expense]
        for (Expense expense : expenses) {
            for (UserExpense userExpense : expense.getUserExpenses()) {
                User user = userExpense.getUser();
                double currentOutstandingAmount;
                currentOutstandingAmount = userOutstandingMap.getOrDefault(user, 0.00);
                userOutstandingMap.put(user, getUpdatedOutStandingAmount(currentOutstandingAmount, userExpense));
            }
        }
        // maxHeap for all PAID users [+ve balance]
        PriorityQueue<Map.Entry<User, Double>> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.getValue(), a.getValue()));
        // minHeap for all users who HAS_TO_PAY [-ve balance]
        PriorityQueue<Map.Entry<User, Double>> minHeap = new PriorityQueue<>(Comparator.comparingDouble(Map.Entry::getValue));
        for (Map.Entry<User, Double> entry : userOutstandingMap.entrySet()) {
            if (entry.getValue() <= 0) {
                minHeap.add(entry);
            } else if (entry.getValue() > 0) {
                maxHeap.add(entry);
            } else {
                log.info(entry.getKey().getName() + " is already settled, no need to be part of transactions");
            }
        }
        while (!minHeap.isEmpty()) {
            Map.Entry<User, Double> maxHasToPay = minHeap.poll();
            Map.Entry<User, Double> maxPaid = maxHeap.poll();
            TransactionDTO transaction = new TransactionDTO(
                    maxHasToPay.getKey().getName(),
                    maxPaid.getKey().getName(),
                    Math.min(Math.abs(maxHasToPay.getValue()), Math.abs(maxPaid.getValue())));
            transactionList.add(transaction);
            double outStanding = maxPaid.getValue() + maxHasToPay.getValue();
            if (outStanding < 0) {
                maxHasToPay.setValue(outStanding);
                minHeap.add(maxHasToPay);
            } else if (outStanding > 0) {
                maxPaid.setValue(outStanding);
                maxHeap.add(maxPaid);
            } else {
                log.info("Settled");
            }
        }
        return transactionList;
    }

    private double getUpdatedOutStandingAmount(double currentOutstandingAmount, UserExpense userExpense) {
        if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID)) {
            currentOutstandingAmount = currentOutstandingAmount + userExpense.getAmount();
        } else {
            currentOutstandingAmount = currentOutstandingAmount - userExpense.getAmount();
        }
        return currentOutstandingAmount;
    }
}

/* Settle Up Algorithm

1. Go through all the expenses and find the outstanding amount for each user
    a. Loop through each expense, and for each expense
    b. We will loop through all the userExpense
If userExpense type is PAID, it will be added as +ve
If userExpense type is HAS_TO_PAY, it will be subtracted as -ve
2. All the users with negative balance [HAS_TO_PAY] => MinHeap
3. All the users with positive balance [PAID] => MaxHeap
4. We will find the transactions

{A:-200, B:-100} - minHeap
{C:150, D:150} - maxHeap

A ==> -200
C ==> 150

C + A ==> 150 - 200 => -50 --> Min Heap

B ==> -100
D ==> 150

D + B ==> 150 - 100 => 50 --> Max Heap

Output = 0 --> Nothing will go back */