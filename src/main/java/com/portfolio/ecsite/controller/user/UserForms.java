package com.portfolio.ecsite.controller.user;

import com.portfolio.ecsite.validation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserForms {

    @NotBlank
    @Size(max=20, message = "20文字以下で入力してください")
    @UniqueUsername
    private String userName;

    @NotBlank
    @Size(min = 12, max = 128, message = "12～20文字で入力してください")
    private  String password;

    @NotBlank
    private String authority;

    @NotBlank
    @Size(max = 100, message = "100文字以下で入力してください")
    private String campany;

    @NotBlank
    @Size(max = 100, message = "100文字以下で入力してください")
    private String address;

    @NotBlank
    @Size(max = 255, message = "255文字以下で入力してください")
    private String phone;
}
