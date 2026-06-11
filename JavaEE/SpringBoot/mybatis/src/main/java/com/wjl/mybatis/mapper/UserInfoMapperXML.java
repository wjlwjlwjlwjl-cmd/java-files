package com.wjl.mybatis.mapper;

import com.wjl.mybatis.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapperXML {
    public List<UserInfo> selectAll();
    public Integer insertOne(UserInfo userInfo);
    public void updateAge(String userName, Integer userAge);
    public void deleteByName(String userName);
    public List<UserInfo> sqlInjection(String userName, String passWord);
    public void insertIf(String userName, Integer userAge, String password);
    public List<UserInfo> selectByCondition(String userName, Integer userAge, String password);
    public void updateByCondition(String userName, Integer userAge, String password);
    public List<UserInfo> selectForEach(List<Integer> ids);
    public void insertForEach(List<UserInfo> userInfos);
    public List<UserInfo> selectBySql();
}
