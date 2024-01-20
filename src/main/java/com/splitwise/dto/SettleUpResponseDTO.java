package com.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor // No-Args or Default Constructor
@AllArgsConstructor // Parameterised Constructor
public class SettleUpResponseDTO {
    private List<TransactionDTO> transactionList;
}
