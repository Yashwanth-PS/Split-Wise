package com.splitwise.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
public class GetUserResponseDTO {
    private  Long userId;
    private String userName;
    private  String  phone;
    private ResponseStatus responseStatus;
    private String message;
}
// "Improved Security: To prioritize privacy, we've skipped displaying complete user model.
// Only necessary user details are now presented for a safer experience."