package com.portfolio.ecsite.service.item;

import lombok.Value;

@Value
public class ItemEntity {

    Long id;
    String itemName;
    String description;
    String fileName;
    String itemImage;
    String company;
    String price;
    Integer stock;
    String payment;
}
