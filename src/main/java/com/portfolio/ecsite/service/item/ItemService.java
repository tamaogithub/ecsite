package com.portfolio.ecsite.service.item;

import com.portfolio.ecsite.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    public ItemEntity find(Long itemId) {
        // レコードが1件も取得できない事も考慮にいれて Optional を返すようにする
        return itemRepository.select(itemId)
                .map(record -> new ItemEntity(record.getId(), record.getItemName()))
                .orElseThrow(() -> new ItemEntityNotFoundException(itemId));
    }

    public ItemEntity create(String itemName) {
        return new ItemEntity(999L,itemName);
//        var record = new ItemRecord(null,itemName);
//        itemRepository.insert(record);
//        return new ItemEntity(record.getId(), record.getItemName());
    }
}
