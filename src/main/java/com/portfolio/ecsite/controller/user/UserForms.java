package com.portfolio.ecsite.controller.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class UserForms {

    @NotBlank
    @Size(min = 1, max=20)
    private String userName;

    @NotBlank
    @Size(min = 12, max = 128)
    private  String password;

    @NotBlank
    private String authority;

    @NotBlank
    @Size(min = 1, max = 100)
    private String campany;

    @NotBlank
    @Size(min = 1, max = 100)
    private String address;

    @NotBlank
    @Size(min = 1, max = 255)
    private String phone;
}
