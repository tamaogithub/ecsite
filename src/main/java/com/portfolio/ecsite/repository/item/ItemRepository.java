package com.portfolio.ecsite.repository.item;

import com.portfolio.ecsite.service.item.ItemEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemRepository {

    @Select("SELECT * FROM items LIMIT #{limit} OFFSET #{offset}")
    List<ItemRecord> selectList(int limit, long offset);

    @Select("SELECT count(*) FROM items")
    int selectListCount();

    @Select("select * from items WHERE id = #{itemId}")
    Optional<ItemRecord> select(Long itemId);

    @Select("select * from items where id = #{itemId}")
    ItemEntity findById(Long itemId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into items (itemname, description, filename, itemimage, company, price, stock, payment) values " +
            "(#{itemName}, #{description}, #{fileName}, #{itemImage}, #{company}, #{price}, #{stock}, #{payment})")
    void insert(ItemRecord record);

    @Update("update items set itemname = #{itemName}, description = #{description}, filename = #{fileName}, " +
            "itemimage = #{itemImage}, company = #{company}, price = #{price}, stock = #{stock} where id = #{itemId}")
    void update(Long itemId, String itemName, String description, String fileName, String itemImage ,String company, String price, Integer stock);

    @Update("update items set itemname = #{itemName}, description = #{description}, filename = #{fileName}, " +
            "company = #{company}, price = #{price}, stock = #{stock} where id = #{itemId}")
    void updateExpectItemImage(Long itemId, String itemName, String description, String fileName, String company, String price, Integer stock);
    @Update("update items set stock = #{stock}, payment = #{payment} where id = #{itemId}")
    void itemBuy(Long itemId, Integer stock, String payment);

//    @Insert("insert into items (user_id, item_id, quantity) values (#{userId}, #{itemId}, #{quantity})")
//    void orderInsert(Long userId, Long itemId, Integer quantity);

    @Select("SELECT stock FROM items where id = #{itemId}")
    int getStock(Long itemId);

    @Delete("DELETE FROM items WHERE id = #{itemId}")
    void delete(Long itemId);
}
