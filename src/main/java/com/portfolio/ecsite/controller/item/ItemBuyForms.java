package com.portfolio.ecsite.controller.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemBuyForms {

    @Min(value = 1, message = "{value}以上で入力してください")
    @Max(value = 1000000000, message = "{value}以下で入力してください")
    @NotNull(message = "空白は許可されていません")
    private Integer stock;

    @NotBlank
    @Size(max = 100, message = "100文字以下で入力してください")
    private String address;

    @NotNull(message = "空白は許可されていません")
    private String payment;

    /** 支払い方法のMapオブジェクト */
    public Map<String,String> getPaymentItems(){
        Map<String, String> paymentMap = new LinkedHashMap<String, String>();
        paymentMap.put("1", "請求書払い");
        paymentMap.put("2", "口座振替");
        paymentMap.put("3", "クレジットカード");
        return paymentMap;
    }
}
