package com.portfolio.ecsite.repository.item;

import lombok.Value;

@Value
public class ItemRecord {

    Long id;
    String itemName;
    String description;
    byte[] itemImage;
    String company;
    int price;
    int stock;
}
