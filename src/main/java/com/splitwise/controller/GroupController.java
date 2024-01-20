package com.splitwise.controller;

import com.splitwise.dto.SettleGroupRequestDTO;
import com.splitwise.dto.SettleGroupResponseDTO;
import com.splitwise.model.constant.ResponseStatus;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

    public SettleGroupResponseDTO settleGroup(SettleGroupRequestDTO requestDTO) {
        SettleGroupResponseDTO responseDTO = new SettleGroupResponseDTO();
        responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        responseDTO.setMessage("Not Implemented Yet!!!");
        return responseDTO;
    }
}