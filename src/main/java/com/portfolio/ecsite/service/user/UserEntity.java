package com.portfolio.ecsite.service.user;

import lombok.Value;

@Value
public class UserEntity {
    long id;
    String userName;
    String password;
    String Authority;
    String campany;
    String address;
    String phone;
}