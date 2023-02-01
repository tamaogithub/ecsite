package com.portfolio.ecsite.repository.user;

import lombok.Value;

@Value
public class UserRecord {

    Long id;
    String userName;
    String password;
    String authority;
    String campany;
    String address;
    String phone;
}
