package com.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserRequestDTO {
    private String userName;
    private String email;
    private String phone;
    private String password;
}