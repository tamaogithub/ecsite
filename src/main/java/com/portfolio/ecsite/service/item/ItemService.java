package com.portfolio.ecsite.service.item;

import com.portfolio.ecsite.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    public ItemEntity find() {
        // レコードが1件も取得できない事も考慮にいれて Optional を返すようにする
        return itemRepository.select()
                .map(record -> new ItemEntity(record.getId(), record.getTitle()))
                .orElseThrow(() -> new IllegalArgumentException("TODO")); //TODO
//        return new ItemEntity(2, "from Service");
    }
}