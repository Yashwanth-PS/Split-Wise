package com.splitwise.controller;

import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

    public  SettleGroupResponseDto settleGroup(SettleGroupRequestDto requestDto){
        SettleGroupResponseDto  responseDto  = new SettleGroupResponseDto();
        responseDto.setResponseStatus(ResponseStatus.FAILURE);
        responseDto.setMessage("not  implemented  yet");
        return  responseDto;
    }
}