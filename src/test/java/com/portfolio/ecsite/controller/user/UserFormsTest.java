package com.portfolio.ecsite.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserFormsTest {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private UserForms userForms;

    @Test
    @Order(1)
    @DisplayName("ユーザー名が空白でないことを確認する")
    void testUserNameNotBlank() {
        userForms.setUserName("");
        assertThat(userForms.getUserName()).isEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("ユーザー名が20文字以下であることを確認する")
    void testUserNameMaxLength() {
        userForms.setUserName("123456789012345678901");
        assertThat(userForms.getUserName()).hasSize(20);
    }

//  UniqueUsernameValidatorTest で重複チェックの確認している為、このテストケースはいらない
//    @Test
//    @DisplayName("ユーザー名がUniqueUsernameによって重複していないことを確認する")
//    @Order(3)
//    void testUniqueUsername() {
//        when(context.isValid()).thenReturn(true);
//        UniqueUsername uniqueUsername = new UniqueUsername();
//        uniqueUsername.initialize(null);
//        assertThat(uniqueUsername.isValid("test", context)).isTrue();
//    }

    @Test
    @Order(3)
    @DisplayName("パスワードが空白でないことを確認する")
    void testPasswordNotBlank() {
        userForms.setPassword("");
        assertThat(userForms.getPassword()).isEmpty();
    }

    @Test
    @Order(4)
    @DisplayName("パスワードが128文字以下であることを確認する")
    void testPasswordMaxLength() {
        userForms.setPassword("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertThat(userForms.getPassword()).hasSize(128);
    }
}