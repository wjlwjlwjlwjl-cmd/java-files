package com.example.mybatis_plus.mapper;

import com.example.mybatis_plus.model.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private ApplicationContext context;

    @BeforeEach
    public void setUp(){
        System.out.println("=========================");
    }

    @AfterEach
    public void TearDown(){
        System.out.println("=========================");
    }

    @Test
    void selectById() {
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        UserInfo userInfo = mapper.selectById(61464578);
        System.out.println(userInfo);
    }

    @Test
    void insertOne() {
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("3943");
        userInfo.setPassword("9388492");
        mapper.insert(userInfo);
    }

    @Test
    void updateById() {
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(61464578);
        userInfo.setUserName("name updated");
        mapper.updateById(userInfo);
    }

    @Test
    void deleteById() {
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        mapper.deleteById(61464578);
    }
}