package com.wjl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wjl.dao.UserDao;

@Component
public class UserService {
    @Autowired
    private UserDao dao;
    public int register(String name, String password){
        if(!StringUtils.hasLength(name) || !StringUtils.hasLength(password)){
            return 1;
        }
        if(dao.exists(name)){
            return 2;
        }
        dao.newUser(name, password);
        return 0;
    }

    public int login(String name, String password){
        if(!StringUtils.hasLength(name) || !StringUtils.hasLength(password)){
            return 1;
        }
        if(!dao.verify(name, password)){
            return 2;
        }
        return 0;
    }   
}
