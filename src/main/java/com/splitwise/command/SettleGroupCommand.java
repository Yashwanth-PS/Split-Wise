package com.splitwise.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//	settleGroup <<groupId>>  two paramaters in it

@Component  //  first it will create the  oject of groupcontroller  and  it will create the object of Settlegroupcommand
public class SettleGroupCommand implements  Command{

    private GroupController groupController;


    @Autowired    //@repository @serice  @restcontroller  @component  at application  start spring will create one  object
    public  SettleGroupCommand(GroupController groupController){ // what ever  object  created in the  groupcontroller will be passed over  here  by spring  Dependency injection( topoogical sort
        this.groupController = groupController;

    }
    @Override
    public boolean canExecute(String input) {
        if  (!input.startsWith("settleGroup")){
            return  false;
        }
        if  (input.split(" ").length!=2){
            return false;
        }
        String[] arr  = input.split(" ");
        try {
            long  groupId = Long.parseLong(arr[1]);
        }
        catch (NumberFormatException numberFormatException){
            return false;

        }
        return  true;
    }

    @Override
    public void execute(String input) {
        //which object do we create here

//        we will create  settleGroupRequestDto and  GroupController
        // requestDto
        //controller

        String [] arr = input.split(" ");
        long  groupId  = Long.parseLong(arr[1]);
        SettleGroupRequestDto  requestDto = new SettleGroupRequestDto();
        requestDto.setGroupId(groupId);
        SettleGroupResponseDto  responseDto = groupController.settleGroup(requestDto);

        if (responseDto.getResponseStatus() == ResponseStatus.SUCCESS){
            List<Transaction>  transactions = responseDto.getTransactions();
            for(Transaction transaction :transactions){
                System.out.println(transaction);
            }
        }
        else {
            System.out.println("settle group failed  with mesage= "+ responseDto.getMessage());
        }

    }
}