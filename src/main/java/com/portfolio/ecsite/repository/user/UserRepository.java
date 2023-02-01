package com.portfolio.ecsite.repository.user;

import com.portfolio.ecsite.service.user.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {

    @Select("select * from users LIMIT #{limit} OFFSET #{offset}")
    List<UserEntity> findAll(int limit , long offset);
    @Select("SELECT count(*) FROM users")
    int selectListCount();
    @Select("select * from users where username = #{userName}")
    Optional<UserEntity> findByUsername(String userName);

    @Select("select * from users WHERE id = #{userId}")
    Optional<UserRecord> select(Long userId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into users (username, password, authority, campany, address, phone) values " +
            "(#{userName}, #{password}, #{authority}, #{campany}, #{address}, #{phone})")
    void insert(UserRecord record);

    @Update("update users set username = #{userName}, password = #{password}, " +
            "authority = #{authority}, campany = #{campany}, address = #{address} where id = #{userId}")
    void update(Long userId, String userName, String password, String authority,
                String campany, String address, String phone);

}