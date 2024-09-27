package com.example.store;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Validator {

public boolean phoneValidator(String phoneNumber){
    int i=0;
    if(phoneNumber.length()>9){
        for(i=0;i<phoneNumber.length();i++){
            if(!Character.isDigit(phoneNumber.charAt(i))){
                break;
            }

        }
    }
    if(i==phoneNumber.length()){
            return true;
        }else{
            return false;
        }
}



public boolean usernameValidator(String userName){
    int i=0;
    if(userName.length()<11){
        for(i=0;i<userName.length();i++){
            if(!Character.isDigit(userName.charAt(i)) && !Character.isLetter(userName.charAt(i)) && userName.charAt(i)!='_'){
                break;
            }

        }
    }
    if(i==userName.length()){
            return true;
        }else{
            return false;
        }
}
}

