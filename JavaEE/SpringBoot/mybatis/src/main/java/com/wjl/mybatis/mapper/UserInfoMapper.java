package com.wjl.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import com.wjl.mybatis.entity.*;

@Mapper //UserInfoMapper 交给 Spring 管理，@Mapper不是 Spring 的注解，而是 Mybatis 告知 Spring 帮助其管理相应类
public interface UserInfoMapper {

    //查找
    @Select("select*from user_info")
    List<UserInfo> selectAll();

    @Select("select*from user_info where user_name= #{userName}")
    List<UserInfo> selectAllByName(String userName);

    @Select("select*from user_info where user_name=#{userName} and user_age=#{userAge};")
    List<UserInfo> selectAllByNameAndAge(String userName, Integer userAge);

    //插入
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user_info(user_name, user_age)" //这里其实就是一个字符串转化为 SQL 语句
        + " values(#{userName}, #{userAge})")
    void insertOne(UserInfo userInfo);

    //删除
    @Delete("delete from user_info where user_name=#{userName};")
    void deleteByName(String userName);

    //更新
    @Update("update user_info set user_name=#{userName} where user_age=#{userAge};")
    void updateAge(String userName, Integer userAge);
}