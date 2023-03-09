package com.portfolio.ecsite.controller.user;

import com.portfolio.ecsite.validation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserForms {

    @NotBlank
    @Size(max=20, message = "20文字以下で入力してください")
    @UniqueUsername
    private String userName;

    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)[a-zA-Z\\d]{12,}$", message = "パスワードは12文字以上で、少なくとも1つの小文字、1つの大文字、1つの数字を含めてください")
    private  String password;

    @NotBlank
    private String authority;

    @NotBlank
    @Size(max = 100, message = "100文字以下で入力してください")
    private String campany;

    @NotBlank
    @Size(max = 100, message = "100文字以下で入力してください")
    private String address;

    @Pattern(regexp = "^0\\d{1,4}-\\d{1,4}-\\d{4}$", message = "有効な電話番号を入力してください")
    private String phone;
}
