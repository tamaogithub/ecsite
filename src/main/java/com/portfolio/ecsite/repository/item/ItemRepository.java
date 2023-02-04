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
    @Insert("insert into items (itemname, description, filename, itemimage, company, price, stock) values " +
            "(#{itemName}, #{description}, #{fileName}, #{itemImage}, #{company}, #{price}, #{stock})")
    void insert(ItemRecord record);

    @Update("update items set itemname = #{itemName}, description = #{description}, filename = #{fileName}, " +
            "itemimage = #{itemImage}, company = #{company}, price = #{price}, stock = #{stock} where id = #{itemId}")
    void update(Long itemId, String itemName, String description, String fileName, String itemImage ,String company, int price, int stock);
}
