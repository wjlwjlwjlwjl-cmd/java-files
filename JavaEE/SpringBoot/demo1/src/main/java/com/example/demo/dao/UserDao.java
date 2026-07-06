package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<UserInfo> {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user_info(username, password) values(#{username}, #{password})")
    public int insertOne(UserInfo userInfo);
}