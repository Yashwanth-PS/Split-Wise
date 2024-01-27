package com.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponseDTO {
    private Long userId;
    private String userName;
    private String phone;
    private String email;
}
// "Improved Security: To prioritize privacy, we've skipped displaying complete user model.
// Only necessary user details are now presented for a safer experience."