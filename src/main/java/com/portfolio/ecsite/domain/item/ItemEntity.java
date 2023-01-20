package com.portfolio.ecsite.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemEntity {
    private long id;
    private String itemName;
    private String description;
    private String company;
    private int price;
    private int stock;
}
