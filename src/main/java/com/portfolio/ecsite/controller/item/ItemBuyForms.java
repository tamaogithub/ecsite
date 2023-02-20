package com.portfolio.ecsite.controller.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemBuyForms {

    private int stock;
    private String payment;
}
