package com.portfolio.ecsite.service.item;

import com.portfolio.ecsite.repository.item.ItemRecord;
import com.portfolio.ecsite.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemEntity find(Long itemId) {
        // レコードが1件も取得できない事も考慮にいれて Optional を返すようにする
        return itemRepository.select(itemId)
                .map(record -> new ItemEntity(
                        record.getId(),
                        record.getItemName(),
                        record.getDescription(),
                        record.getItemImage(),
                        record.getCompany(),
                        record.getPrice(),
                        record.getStock()))
                .orElseThrow(() -> new ItemEntityNotFoundException(itemId));
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ItemEntity> findAll(int limit ,long offset) {
        //取得したList<itemRecord>をstream()でItemEntityに変換し、最後にList<ItemEntity>に変換する
        return itemRepository.selectList(limit, offset)
                .stream()
                .map(record -> new ItemEntity(
                        record.getId(),
                        record.getItemName(),
                        record.getDescription(),
                        record.getItemImage(),
                        record.getCompany(),
                        record.getPrice(),
                        record.getPrice()))
                .collect(Collectors.toList());
    }

    public int findTotal() {
        return itemRepository.selectListCount();
    }


    @Transactional
    public ItemEntity create(String itemName, String  description, byte[] itemImage, String company, int price, int stock) {
        var record = new ItemRecord(null,itemName, description, itemImage ,company, price, stock);
        itemRepository.insert(record);

        return new ItemEntity(
                record.getId(),
                record.getItemName(),
                record.getDescription(),
                record.getItemImage(),
                record.getCompany(),
                record.getPrice(),
                record.getStock());
    }

    @Transactional
    public void update(Long itemId, String itemName, String description, byte[] itemImage, String company, int price, int stock) {
        itemRepository.update(itemId, itemName, description, itemImage, company, price, stock);
    }

}
