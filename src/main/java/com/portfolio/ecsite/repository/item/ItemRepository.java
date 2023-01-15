package com.portfolio.ecsite.repository.item;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface ItemRepository {

    @Select("SELECT id , itemname FROM items WHERE id = #{itemId}")
    Optional<ItemRecord> select(Long itemId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO items (itemname) VALUES (#{itemName})")
    void insert(ItemRecord record);
}