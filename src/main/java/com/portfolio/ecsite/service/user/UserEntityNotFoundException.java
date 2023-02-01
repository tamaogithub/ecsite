package com.portfolio.ecsite.service.user;

public class UserEntityNotFoundException extends RuntimeException{

    private long userId;

    public UserEntityNotFoundException(long userId){
        super("UserEntity ( id = " + userId + ") is not found.");
        this.userId = userId;
    }
}
