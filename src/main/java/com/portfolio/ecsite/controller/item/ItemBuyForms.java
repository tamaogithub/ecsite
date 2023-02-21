package com.portfolio.ecsite.controller.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class ItemBuyForms {

    private int stock;
    private String address;
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
