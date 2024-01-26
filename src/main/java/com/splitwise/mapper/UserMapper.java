package com.splitwise.mapper;

import com.splitwise.dto.GetUserResponseDTO;
import com.splitwise.dto.RegisterUserRequestDTO;
import com.splitwise.dto.RegisterUserResponseDTO;
import com.splitwise.model.User;

public class UserMapper {

    public static User mapRequestDTOToUser(RegisterUserRequestDTO requestDTO) {
        User user = new User();
        user.setName(requestDTO.getUserName());
        user.setEmail(requestDTO.getEmail());
        user.setPhoneNumber(requestDTO.getPhone());
        user.setPassword(requestDTO.getPassword());
        return user;
    }

    public static RegisterUserResponseDTO mapRequestUserToDTO(User user) {
        RegisterUserResponseDTO responseDTO = new RegisterUserResponseDTO();
        responseDTO.setUserName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPhone(user.getPhoneNumber());
        return responseDTO;
    }

    public static GetUserResponseDTO mapUserToUserResponseDTO(User user) {
        GetUserResponseDTO responseDTO = new GetUserResponseDTO();
        responseDTO.setUserId(user.getId());
        responseDTO.setUserName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPhone(user.getPhoneNumber());
        return responseDTO;
    }
}