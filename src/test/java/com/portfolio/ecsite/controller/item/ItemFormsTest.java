package com.portfolio.ecsite.controller.item;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayName("商品登録、編集画面のフォームのテスト")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemFormsTest {

    @InjectMocks
    private ItemForms itemForms;
    private Validator validator;

    private MockMultipartFile mockMultipartFile;

    private Set<ConstraintViolation<ItemForms>> violations;

    private ConstraintViolation<ItemForms> violation;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        // バリデータのファクトリを取得
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // バリデータを取得
        validator = factory.getValidator();
        // 画像ファイルを含むMockMultipartFileオブジェクトを作成する
        mockMultipartFile = new MockMultipartFile("file", "image.jpg", "image/jpeg", getClass().getResourceAsStream("/c/tmp/image.jpg"));
        // バリデーション対象のオブジェクトを生成
        itemForms = new ItemForms("歯ブラシ", "歯ブラシ（極細）",  mockMultipartFile, "歯ブラシ2.jpg", "ELECOM", "157", 10);
    }
    @Test
    @Order(1)
    @DisplayName("正常系：フォームにバリデーションが全て成功すること")
    void testValidation() {
        // バリデーション対象のオブジェクトを生成し、バリデーションがないかチェック
        assertTrue(validator.validate(itemForms).isEmpty());
    }

    @Test
    @Order(2)
    @DisplayName("異常系：フォームにバリデーションエラーが5件あること")
    void testInvalidValidation() {
        // バリデーション対象のオブジェクトを生成（バリデーションエラーあり）
        ItemForms itemForms = new ItemForms("", "", null, "azarashi.png", "", null, null);
        // バリデーション対象のオブジェクトを生成し、バリデーションの件数を取得
        var violationsSize = validator.validate(itemForms).size();
        //バリデーションがあるかをチェック
        assertEquals(5, violationsSize);
    }

    @Test
    @Order(3)
    @DisplayName("異常系：商品名が空白の場合、バリデーションエラーになること")
    void testBlankItemName() {
        itemForms.setItemName("");
        violations = validator.validate(itemForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("空白は許可されていません", violation.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("異常系：商品名が最大文字数を超えた場合、バリデーションエラーになること")
    void testMaxItemName() {
        itemForms.setItemName("a".repeat(21));
        violations = validator.validate(itemForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("20文字以下で入力してください", violation.getMessage());
    }

    @Test
    @Order(5)
    @DisplayName("異常系：商品概要が空白の場合、バリデーションエラーになること")
    void testBlankDescription() {
        itemForms.setDescription("");
        violations = validator.validate(itemForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("空白は許可されていません", violation.getMessage());
    }

    @Test
    @Order(6)
    @DisplayName("異常系：商品説明が最大文字数を超えた場合、バリデーションエラーになること")
    void testMaxDescription() {
        itemForms.setDescription("a".repeat(101));
        violations = validator.validate(itemForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("100文字以下で入力してください", violation.getMessage());
    }

    @Test
    @Order(7)
    @DisplayName("異常系：fileNameの最大文字数を超えた場合、バリデーションエラーになること")
    void testSizeCheckOfFileName() {
        itemForms.setFileName("a".repeat(256));
        violations = validator.validate(itemForms);
        assertEquals(violations.size(), 1);
        assertEquals("255文字以下で入力してください", violations.iterator().next().getMessage());
    }
    @Test
    @Order(8)
    @DisplayName("異常系：販売会社が空白の場合、バリデーションエラーになること")
    void testBlankCompany() {
        itemForms.setCompany("");
        violations = validator.validate(itemForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("空白は許可されていません", violation.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("異常系：販売会社が最大値以上の場合、バリデーションエラーになること")
    void testSizeCheckOfCompany() {
        itemForms.setCompany("a".repeat(101));
        violations = validator.validate(itemForms);
        assertEquals(violations.size(), 1);
        assertEquals("100文字以下で入力してください", violations.iterator().next().getMessage());
    }

    @Test
    @Order(10)
    @DisplayName("異常系：販売価格（円）がnullの場合、バリデーションエラーになること")
    void testNullPrice() {
        itemForms.setPrice(null);
        violations = validator.validate(itemForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("空白は許可されていません", violation.getMessage());
    }

    @Test
    @Order(11)
    @DisplayName("異常系：販売価格（円）が最小値以下の場合、バリデーションエラーになること")
    void testPriceMinValidation() {
        itemForms.setPrice("0");
        violations = validator.validate(itemForms);

        assertEquals(1, violations.size());
        assertEquals("1以上で入力してください", violations.iterator().next().getMessage());
        itemForms.setPrice("-1");
        assertEquals(1, violations.size());
        assertEquals("1以上で入力してください", violations.iterator().next().getMessage());
    }

    @Test
    @Order(12)
    @DisplayName("異常系：販売価格（円）が最大値以上の場合、バリデーションエラーになること")
    void testPriceMaxValidation() {
        itemForms.setPrice("1000000001");
        violations = validator.validate(itemForms);

        assertEquals(1, violations.size());
        assertEquals("1000000000以下で入力してください", violations.iterator().next().getMessage());
    }
    @Test
    @Order(13)
    @DisplayName("異常系：在庫数（個）がnullの場合、バリデーションエラーになること")
    void testNullStock() {
        itemForms.setStock(null);
        violations = validator.validate(itemForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("空白は許可されていません", violation.getMessage());
    }

    @Test
    @Order(14)
    @DisplayName("正常系：在庫数（個）が最小値の場合、バリデーションに成功する")
    void testStockMinValidation() {
        itemForms.setStock(1);
        violations = validator.validate(itemForms);
        assertEquals(0, violations.size());
    }

    @Test
    @Order(15)
    @DisplayName("正常系：在庫数（個）が最大値の場合、バリデーションエラーに成功する")
    void testStockMaxOkValidation() {
        itemForms.setStock(1000000000);
        violations = validator.validate(itemForms);
        assertEquals(0, violations.size());
    }
    @Test
    @Order(16)
    @DisplayName("異常系：在庫数（個）が最大値以上の場合、バリデーションエラーになること")
    void testStockMaxValidation() {
        itemForms.setStock(1000000001);
        violations = validator.validate(itemForms);
        assertEquals(1, violations.size());
        assertEquals("1000000000以下で入力してください", violations.iterator().next().getMessage());
    }
}
