package com.splitwise.dto;

import com.splitwise.model.constant.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserResponseDTO {
    private Long userId;
    private ResponseStatus responseStatus;
    private String message;

}