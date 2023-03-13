package com.portfolio.ecsite.service.user;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UserEntity {
    Long id;
    String userName;
    String password;
    String Authority;
    String campany;
    String address;
    String phone;

    public void setUsername(String username) {
    }

}