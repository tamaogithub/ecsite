package com.portfolio.ecsite.service.item;

public class ItemEntityNotFoundException extends RuntimeException{

    private Long itemId;

    public ItemEntityNotFoundException(Long itemId){
        super("ItemEntity ( id = " + itemId + ") is not found.");
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
    }
}
