package com.example.tkmybatisdemo.dao;

import com.example.tkmybatisdemo.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
    @Select("select * from user")
    List<User> getAll();
}
