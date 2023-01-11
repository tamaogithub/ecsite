package com.portfolio.ecsite.repository.item;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface ItemRepository {

    @Select("SELECT id , title FROM tasks ORDER BY id LIMIT 1")
    Optional<ItemRecode> select();
}