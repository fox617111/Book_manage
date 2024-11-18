package org.example.book_manage.mapper;

import org.apache.ibatis.annotations.*;
import org.example.book_manage.pojo.User;


@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{md5String}")
    User SelectUser(String username, String md5String);

    @Insert("INSERT INTO user (username, password) VALUES (#{username}, #{password})")
    void InsertUser(@Param("username") String username, @Param("password") String password);

    @Update("update user set password=#{newpassword} where id=#{id}")
    void updatePassword(Integer id, String newpassword);

    void update(User user);

    @Delete("delete from user where id=#{id}")
    void delete(Integer id);
}
