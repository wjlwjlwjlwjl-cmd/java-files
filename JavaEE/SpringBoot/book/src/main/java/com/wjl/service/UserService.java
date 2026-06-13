package com.wjl.service;

import com.wjl.entity.UserInfo;
import com.wjl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    @Autowired
    private ApplicationContext context;

    public int login(String userName, String userPassword){
        if(!StringUtils.hasLength(userName) || !StringUtils.hasLength(userPassword)){
            return 1;
        }
        UserMapper mapper = context.getBean(UserMapper.class);
        UserInfo ret = mapper.login(userName, userPassword);
        if(ret == null){
            return 1;
        }
        return 0;
    }

    public int register(String userName, String userPassword){
        UserMapper mapper = context.getBean(UserMapper.class);
        String ret = mapper.exists(userName);
        if(ret != null){
            return 1;
        }
        mapper.register(userName, userPassword);
        return 0;
    }
}
