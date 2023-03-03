package com.portfolio.ecsite.controller.item;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@DisplayName("商品購入画面のフォームのテスト")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemBuyFormsTest {

    @Autowired
    ItemController itemController;
    @Mock
    private Map<String,String> paymentItemsMock;

    @InjectMocks
    ItemBuyForms itemBuyForms;

    Validator validator;

    MockMultipartFile mockMultipartFile;

    ItemBuyForms itemForms;

    private Map<String,String> paymentItems;

    Set<ConstraintViolation<ItemBuyForms>> violations;

    ConstraintViolation<ItemBuyForms> violation;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        // バリデータのファクトリを取得
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // バリデータを取得
        validator = factory.getValidator();
        // 画像ファイルを含むMockMultipartFileオブジェクトを作成する
        mockMultipartFile = new MockMultipartFile("file", "image.jpg", "image/jpeg", getClass().getResourceAsStream("/c/tmp/image.jpg"));
        //支払い方法のMapオブジェクト取得
//        paymentItems = itemController.getPaymentItems();
        // バリデーション対象のオブジェクトを生成
        itemBuyForms = new ItemBuyForms(1, "埼玉県", "請求書払い");
        //支払い方法のMapオブジェクトを生成
        paymentItems = new HashMap<>();

        //itemBuyForms = new ItemBuyForms(paymentItemsMock);
    }

    @Test
    @Order(1)
    @DisplayName("支払い方法のMapオブジェクトのテスト")
    @Disabled
    void testGetPaymentItems() {
        // Mockの設定
        when(paymentItemsMock.put("1", "請求書払い")).thenReturn("1");
        when(paymentItemsMock.put("2", "口座振替")).thenReturn(null);
        when(paymentItemsMock.put("3", "クレジットカード")).thenReturn(null);

        // 検証
        assertEquals(paymentItemsMock.size(), itemBuyForms.getPaymentItems().size());
        assertEquals(paymentItemsMock, itemBuyForms.getPaymentItems());
    }

    @Test
    @Order(5)
    @DisplayName("異常系：個数がnullの場合、バリデーションエラーになること")
    void testNullStock() {
        itemBuyForms.setStock(null);
        Set<ConstraintViolation<ItemBuyForms>> violations = validator.validate(itemBuyForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("空白は許可されていません", violation.getMessage());
    }

    @Test
    @Order(2)
    @DisplayName("異常系：個数が最小値以下の場合、バリデーションエラーになること")
    void testStockMinValue() {
        // テスト対象フィールドの設定
        itemBuyForms.setStock(0);
        violations = validator.validate(itemBuyForms);
        assertEquals(1, violations.size());
        assertEquals("1以上で入力してください", violations.iterator().next().getMessage());
        itemBuyForms.setStock(-1);
        assertEquals(1, violations.size());
        assertEquals("1以上で入力してください", violations.iterator().next().getMessage());
    }

    @Test
    @Order(3)
    @DisplayName("異常系：個数が最小値以上の場合、バリデーションエラーになること")
    void testStockMaxValue() {
        itemBuyForms.setStock(1000000001);
        violations = validator.validate(itemBuyForms);
        assertEquals(1, violations.size());
        assertEquals("1000000000以下で入力してください", violations.iterator().next().getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("異常系：配送先が空白の場合、バリデーションエラーになること")
    void testBlankAdress() {
        itemBuyForms.setAddress("");
        violations = validator.validate(itemBuyForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("空白は許可されていません", violation.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("異常系：配送先が最大文字数を超えた場合、バリデーションエラーになること")
    void testAddressMaxSize() {
        itemBuyForms.setAddress("a".repeat(101));
        violations = validator.validate(itemBuyForms);
        Assertions.assertEquals(1, violations.size());
        violation = violations.iterator().next();
        Assertions.assertEquals("100文字以下で入力してください", violation.getMessage());
    }


    @Test
    @Order(5)
    @DisplayName("正常系：支払い方法のフィールドのテスト")
    void testPayment() {
        // テスト対象フィールドの設定
        itemBuyForms.setPayment("クレジットカード");
        // 検証
        assertEquals("クレジットカード", itemBuyForms.getPayment());
    }
}
