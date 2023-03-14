package com.portfolio.ecsite.controller.user;

import com.portfolio.ecsite.repository.user.UserRepository;
import com.portfolio.ecsite.validation.UniqueUsername;
import com.portfolio.ecsite.validation.UniqueUsernameValidator;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.*;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("ユーザー登録、編集のフォームのテスト")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserFormsTest {

    @InjectMocks
    private UserForms userForms;
    private Validator validator;
    UniqueUsernameValidator uniqueUsernameValidator;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UniqueUsername uniqueUsername;

    @Mock
    private ConstraintValidatorContext context;

    Set<ConstraintViolation<UserForms>> violations;

    ConstraintViolation<UserForms> violation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // バリデータのファクトリを取得
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // バリデータを取得
        validator = factory.getValidator();

        uniqueUsernameValidator = new UniqueUsernameValidator(userRepository);
        // バリデーション対象のオブジェクトを生成
        UserForms userForms = new UserForms("tom", "password1234", "SHOP", "DELL", "埼玉", "080-0000-1111");

    }

    @Test
    @Order(1)
    @DisplayName("正常系：ユーザー名がユニークな場合")
    public void testValidUserName() {
        String username = "tom";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // カスタムバリデータにテスト用のユーザー名とモックされたリポジトリを渡してバリデーションを実行
        boolean result = uniqueUsernameValidator.isValid(username, context);

        assertEquals(true, result);
    }

    @Test
    @Order(2)
    @DisplayName("異常系：ユーザー名が空白の場合、バリデーションエラーになること")
    void testBlankUserName() {
        String username = "testUser";
        userForms.setUserName("");
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        boolean result = uniqueUsernameValidator.isValid(username, context);

        violations = validator.validate(userForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("空白は許可されていません", violation.getMessage());

    }
    @Test
    @Order(3)
    @DisplayName("異常系：ユーザー名が空白でないことを確認する")
    void testUserNameNotBlank() {
        userForms.setUserName("");
        assertThat(userForms.getUserName()).isEmpty();
    }

    @Test
    @Order(4)
    @DisplayName("異常系：ユーザー名が空の場合、バリデーションエラーになること")
    public void testUserNameIsEmpty() {
        userForms.setUserName("");
        String username = userForms.getUserName();
        when(userRepository.findByUsername(userForms.getUserName())).thenReturn(Optional.empty());
        // カスタムバリデータにテスト用のユーザー名とモックされたリポジトリを渡してバリデーションを実行
        boolean result = uniqueUsernameValidator.isValid(username, context);
        assertEquals(true, result);
    }


    @Test
    @Order(5)
    @DisplayName("異常系：ユーザー名が20文字以上の場合、バリデーションに失敗すること")
    void testUserNameMaxLength() {
        // 20文字の文字列を作成
        String maxCharString = "a".repeat(20);
        userForms.setUserName(maxCharString);

        violations = validator.validate(userForms);
        Assertions.assertEquals(1, violations.size());

        // userNameプロパティが20文字であることを確認
        assertEquals(maxCharString, userForms.getUserName());


//        assertThat(userForms.getUserName()).hasSize(20);

        assertThrows(ConstraintViolationException.class, () -> {
            userForms.setUserName("a".repeat(21));
        });
    }

    @Test
    @Order(6)
    @DisplayName("異常系：パスワードが空白の場合、バリデーションエラーになること")
    void testPasswordNotBlank() {
        userForms.setPassword("");
        violations = validator.validate(userForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertThat(userForms.getPassword()).isEmpty();
    }

    @Test
    @Order(7)
    @DisplayName("正常系：パスワードが8文字以上であることを確認する")
    void testPasswordMinLength() {
        userForms.setPassword("a".repeat(8));
        assertThat(userForms.getPassword()).hasSize(8);
    }

    @Test
    @Order(8)
    @DisplayName("正常系：パスワードが128文字以下であることを確認する")
    void testPasswordMaxLength() {
        userForms.setPassword("a".repeat(128));
        assertThat(userForms.getPassword()).hasSize(128);
    }
}