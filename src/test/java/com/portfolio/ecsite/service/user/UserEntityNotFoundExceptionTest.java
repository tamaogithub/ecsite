package com.portfolio.ecsite.service.user;

import com.portfolio.ecsite.repository.user.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@DisplayName("ユーザー編集画面のテスト")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserEntityNotFoundExceptionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @Order(1)
    @DisplayName("存在しないユーザーの検索テスト")
    void testFindNonExistingUser() {
        // モックの準備
        Long userId = 2L;
        when(userRepository.select(userId)).thenReturn(Optional.empty());

        // テスト＆検証
        UserEntityNotFoundException thrown = assertThrows(UserEntityNotFoundException.class, () -> {
            userService.find(userId);
        });
        assertEquals(userId, thrown.getUserId());
    }

    @Test
    @Order(2)
    @DisplayName("ユーザーIDが存在しないエラーメッセージが正しいことを確認するテスト")
    void testErrorMessage() {
        Long userId = 1L;
        UserEntityNotFoundException exception = new UserEntityNotFoundException(userId);
        String expectedMessage = "UserEntity ( id = " + userId + ") is not found.";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @Order(3)
    @DisplayName("ユーザー ID が正しく設定されていることを確認するテスト")
    void testUserId() {
        Long userId = 2L;
        UserEntityNotFoundException exception = new UserEntityNotFoundException(userId);

        assertEquals(userId, exception.getUserId());
    }

}