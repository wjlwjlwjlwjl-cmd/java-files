package com.example.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.mybatis_plus.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//只需要通过继承BaseMapper就可以直接使用mybatis提供的诸多CURD方法
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    public List<UserInfo> selectByCondition();

    @Select("select*from user_info where id=#{id}")
    public UserInfo selectById1(int id);

    public List<UserInfo> selectById2(int id);

    @Select("select*from user_info ${ew.customSqlSegment}")
    public List<UserInfo> selectByCondition1(@Param(Constants.WRAPPER) Wrapper<UserInfo> wrapper);
}