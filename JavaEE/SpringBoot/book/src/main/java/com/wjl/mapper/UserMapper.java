package com.wjl.mapper;

import com.wjl.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select*from user_info where user_name=#{userName} and user_password=#{userPassword}")
    public UserInfo login(String userName, String userPassword);

    @Select("select user_name from user_info where user_name=#{userName}")
    public String exists(String userName);

    @Insert("insert into user_info(user_name, user_password) values(#{userName}, #{userPassword})")
    public void register(String userName, String userPassword);
}
