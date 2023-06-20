package com.example.BankingApplicationServer.util;

public class AccountNotCreatedException extends RuntimeException{
    public AccountNotCreatedException(String msg){
        super(msg);
    }
}
