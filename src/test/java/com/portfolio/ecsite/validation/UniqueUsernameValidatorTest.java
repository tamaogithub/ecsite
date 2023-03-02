package com.portfolio.ecsite.validation;

import com.portfolio.ecsite.repository.user.UserRepository;
import com.portfolio.ecsite.service.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class UniqueUsernameValidatorTest {

    private UniqueUsernameValidator uniqueUsernameValidator;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        uniqueUsernameValidator = new UniqueUsernameValidator(userRepository);
    }

    @Test
    @Order(1)
    @DisplayName("正常系テスト：ユーザ名が重複していない場合、trueが返ることを確認する")
    public void testIsValidWithNonExistingUsername() {
        String username = "testUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        boolean result = uniqueUsernameValidator.isValid(username, context);
        assertTrue(result);
    }

    @Test
    @Order(2)
    @DisplayName("異常系テスト：ユーザ名が重複している場合、falseが返ることを確認する")
    public void testIsValidWithExistingUsername() {
        String username = "testUser";
        UserEntity user = new UserEntity(1,"tom","password1234","SHOP","DELL","埼玉県","080-5555-6666");
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        boolean result = uniqueUsernameValidator.isValid(username, context);
        assertFalse(result);
    }
}
