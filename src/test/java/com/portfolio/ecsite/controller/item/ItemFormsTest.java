package com.portfolio.ecsite.controller.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class ItemFormsTest {

    Validator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // バリデータのファクトリを取得
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // バリデータを取得
        validator = factory.getValidator();
    }
    @Test
    @Order(1)
    @DisplayName("フォームにバリデーションが1件もないかチェック【正常系】")
    void testValidation() throws IOException {
        // 画像ファイルを含むMockMultipartFileオブジェクトを作成する
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "image.jpg", "image/jpeg", getClass().getResourceAsStream("/c/tmp/image.jpg"));
        // バリデーション対象のオブジェクトを生成
        ItemForms itemForms = new ItemForms("歯ブラシ", "歯ブラシ（極細）", "歯ブラシ2.jpg", mockMultipartFile, "ELECOM", 157, 10, null);
        // バリデーションを実行
        var violations = validator.validate(itemForms);
        // バリデーションがないかチェック
        assertTrue(violations.isEmpty());
    }

    @Test
    @Order(2)
    @DisplayName("フォームにバリデーションが5件あるかチェック【異常系】")
    void testInvalidValidation() {
        // バリデーション対象のオブジェクトを生成（バリデーションエラーあり）
        ItemForms itemForms = new ItemForms("", "", "azarashi.png", null, "", null, null, null);
        // バリデーションを実行
        var violations = validator.validate(itemForms);
        var violationsSize = violations.size();
        //バリデーションがあるかをチェック
        assertEquals(5, violationsSize);
    }
}
