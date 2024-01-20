package com.splitwise.dto;

import com.splitwise.model.constant.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleGroupResponseDTO {
    private List<TransactionDTO> transactions;
    private ResponseStatus responseStatus;
    private String message;
}