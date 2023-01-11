package com.portfolio.ecsite.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@EnableGlobalMethodSecurity コメントアウトで ADMIN 認可を無効
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {

}
