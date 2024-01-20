package com.splitwise.dto;

import com.splitwise.model.constant.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponseDTO {
    private Long userId;
    private String userName;
    private String phone;
    private ResponseStatus responseStatus;
    private String message;
}
// "Improved Security: To prioritize privacy, we've skipped displaying complete user model.
// Only necessary user details are now presented for a safer experience."