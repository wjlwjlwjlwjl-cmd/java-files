package com.example.mybatis_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatis_plus.mapper.UserInfoMapper;
import com.example.mybatis_plus.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class UesrInfoController {
    @Autowired
    private UserInfoMapper mapper;

    public UserInfo selectById(){
        return mapper.selectById(1);
    }

    public List<UserInfo> selectByCondition(){
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.select("id, user_name, age").eq("password", 9388492).like("user_name", "43");
        return mapper.selectList(wrapper);
    }
}
