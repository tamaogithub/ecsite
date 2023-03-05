package com.portfolio.ecsite.service.user;

public class UserEntityNotFoundException extends RuntimeException{

    private Long userId;

    public UserEntityNotFoundException(Long userId){
        super("UserEntity ( id = " + userId + ") is not found.");
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
