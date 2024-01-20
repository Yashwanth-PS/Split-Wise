package com.splitwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


// 5 step
//maven  Repository for spring boot starter Web
// update  pom and  refresh maven  to download
// change controller  to restController  makes capable of  recieving the  Http post and  get
// update methods  with  get or  post mapping     1) register user  is post menthod   2) get user is get method
// how to get data // where  tp get  request DTO  for that we need  to say something http is a protocol which has many method  post method and  get method   but  it can send data in two methods  you can send message as body or  header  @RequestBody

//@Controller

@RestController
public class UserController {
    private UserService userService;


    @Autowired
    public  UserController(UserService userService){
        this.userService= userService;
    }
//    if i would send the  data in param  then  @RequestParam will be userd
    @PostMapping("user/register/") //sending the  data
    public RegisterUserResponseDto registerUser( @RequestBody() RegisterUserRequestDto requestDto){
        RegisterUserResponseDto  responseDto  = new RegisterUserResponseDto();
        try {
            User user  = new User();
            user.setUserName(requestDto.getUserName());
            user.setPhone(requestDto.getPhone());
            user.setPassword(requestDto.getPassword());

            User  savedUser  =  userService.registerUser(user);
            responseDto.setUserId(savedUser.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("User  created  Success");

        }catch (Exception ex){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(ex.getMessage());


        }

        return  responseDto;

    }

//    @PostMapping("/user/get/")
//public GetUserResponseDto getUser(@RequestBody GetUserRequestDto requestDto){
//GetUserResponseDto  responseDto  = new GetUserResponseDto ();
//        try {
//        User user  = userService.getUser(requestDto.getUserId());
//        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
//        responseDto.setMessage("User retrived successfully");
//        responseDto.setUserName(user.getUserName());
//        responseDto.setUserId(user.getId());
//        responseDto.setPhone(user.getPhone());
//    public GetUserResponseDto getUser(GetUserRequestDto requestDto){ before  using the  post request


   //using  the  get and  request param
    @GetMapping("/user/get/")
    public GetUserResponseDto getUser(@RequestParam() Long userId){
        GetUserResponseDto  responseDto  = new GetUserResponseDto ();
        try {
             User user  = userService.getUser(userId);
             responseDto.setResponseStatus(ResponseStatus.SUCCESS);
             responseDto.setMessage("User retrived successfully");
             responseDto.setUserName(user.getUserName());
             responseDto.setUserId(user.getId());
             responseDto.setPhone(user.getPhone());


        }catch (Exception ex){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(ex.getMessage());

        }
        return  responseDto;

    }
}