package com.portfolio.ecsite.controller.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class ItemBuyForms {

    @Min(value = 1, message = "{value}以上の値を設定してください")
    @NotNull
    private Integer stock;

    @NotBlank
    @Size(max=100)
    private String address;

    @NotBlank
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
