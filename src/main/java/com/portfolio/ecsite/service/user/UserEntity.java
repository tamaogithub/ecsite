package com.portfolio.ecsite.service.user;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class UserEntity {
    long id;
    String userName;
    String password;
    String Authority;
    String campany;
    String address;
    String phone;
    public void setUsername(String username) {
    }
}