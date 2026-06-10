package com.wjl.mybatis.mapper;

import com.wjl.mybatis.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapperXML {
    public List<UserInfo> selectAll();
    public Integer insertOne(UserInfo userInfo);
    public void updateAge(String userName, Integer userAge);
    public void deleteByName(String userName);
}
