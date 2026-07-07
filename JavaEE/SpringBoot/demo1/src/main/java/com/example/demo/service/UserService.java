package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public int login(String username, String password){
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>()
                .select("username")
                .eq("username", username)
                .eq("password", password);
        List<UserInfo> rets = userDao.selectList(wrapper);
        if(!rets.isEmpty()){
            return 1;
        }
        return 0;
    }

    public int register(String username, String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        int ret = userDao.insertOne(userInfo);
        if(ret != 1){
            return 1;
        }
        try{
            int a = 1 / 0;
        }
        catch(Exception e){
            log.error(e.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return 0;
    }
}