package com.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserResponseDTO {
    private String userName;
    private String email;
    private String phone;
}
