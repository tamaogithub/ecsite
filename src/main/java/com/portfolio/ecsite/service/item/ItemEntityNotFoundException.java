package com.portfolio.ecsite.service.item;

public class ItemEntityNotFoundException extends RuntimeException{

    private long itemId;

    public ItemEntityNotFoundException(long itemId){
        super("ItemEntity ( id = " + itemId + ") is not found.");
        this.itemId = itemId;
    }
}
