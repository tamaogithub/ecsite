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
    @DisplayName("正常系テスト：ユーザ名がユニークの場合、trueが返ることを確認する")
    public void testIsValidWithNonExistingUsername() {
        String username = "testUser";
        //userRepository.findByUsername(username)が呼び出された場合に、Optional.empty()を返すように設定しています。
        //これにより、テスト中にUserRepositoryの実際のデータベースアクセスを行わずに、想定されたバリデーション結果をテストすることができます。
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        boolean result = uniqueUsernameValidator.isValid(username, context);
        assertTrue(result);
    }

    @Test
    @Order(2)
    @DisplayName("異常系テスト：ユーザ名が重複している場合、falseが返ることを確認する")
    public void testIsValidWithExistingUsername() {
        String username = "testUser";
        UserEntity userEntity = new UserEntity(1L,"tom","password1234","SHOP","DELL","埼玉県","080-5555-6666");

        //ユーザー名が"testUser"のUserEntityオブジェクトを作成
        userEntity.setUsername(username);

        //userRepository.findByUsername(username)が呼び出された場合に、Optional.of(userEntity)を返すように設定しています。
        //これにより、指定されたユーザー名が既にデータベースに存在することがシミュレートされます。
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));
        boolean result = uniqueUsernameValidator.isValid(username, context);
        assertFalse(result);
    }
}
