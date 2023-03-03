package com.portfolio.ecsite.controller.item;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("ItemBuyFormsクラスのテスト")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemBuyFormsTest {

    @Mock
    private Map<String,String> paymentItemsMock;

    @InjectMocks
    private ItemBuyForms itemBuyForms;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("支払い方法のMapオブジェクトのテスト")
    void testGetPaymentItems() {
        // Mockの設定
        when(paymentItemsMock.put("1", "請求書払い")).thenReturn(null);
        when(paymentItemsMock.put("2", "口座振替")).thenReturn(null);
        when(paymentItemsMock.put("3", "クレジットカード")).thenReturn(null);

        // テスト対象メソッドの実行
        Map<String,String> paymentItems = itemBuyForms.getPaymentItems();

        // 検証
        assertEquals(paymentItemsMock.size(), paymentItems.size());
        assertEquals(paymentItemsMock, paymentItems);
    }

    @Test
    @Order(2)
    @DisplayName("ItemBuyFormsのstockフィールドの最小値テスト")
    void testStockMinValue() {
        // テスト対象フィールドの設定
        itemBuyForms.setStock(1);

        // 検証
        assertEquals(1, itemBuyForms.getStock());
    }

    @Test
    @Order(3)
    @DisplayName("ItemBuyFormsのaddressフィールドのサイズテスト")
    void testAddressMaxSize() {
        // テスト対象フィールドの設定
        itemBuyForms.setAddress("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        // 検証
        assertEquals(100, itemBuyForms.getAddress().length());
    }

    @Test
    @Order(4)
    @DisplayName("ItemBuyFormsのpaymentフィールドのテスト")
    void testPayment() {
        // テスト対象フィールドの設定
        itemBuyForms.setPayment("クレジットカード");

        // 検証
        assertEquals("クレジットカード", itemBuyForms.getPayment());
    }
}
