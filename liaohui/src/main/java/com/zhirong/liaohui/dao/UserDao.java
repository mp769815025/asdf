package com.zhirong.liaohui.dao;

import com.zhirong.liaohui.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

//    List<User> selectUsers();

    @Select("select * from user")
    public List<User> getAllUsers();


}