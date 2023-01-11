package com.portfolio.ecsite.web.user;

import com.portfolio.ecsite.web.validation.UniqueUsername;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class UserForm {

    @NotBlank
    @UniqueUsername
    private String username;

    @NotBlank
    @Size(min = 12, max = 128)
    private  String password;

    @NotBlank
    private String authority;
}
