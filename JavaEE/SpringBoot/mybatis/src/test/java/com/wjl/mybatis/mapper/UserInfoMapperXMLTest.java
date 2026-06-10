package com.wjl.mybatis.mapper;

import com.wjl.mybatis.entity.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootTest
class UserInfoMapperXMLTest {
    @Autowired
    private ApplicationContext context;

    @BeforeEach
    public void setUp(){
        System.out.println("<<<<<<<<TestBegin>>>>>>>>");
    }

    @AfterEach
    public void TearDown(){
        System.out.println("<<<<<<<<TestEnd>>>>>>>>");
    }

    @Test
    public void selectAll(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        List<UserInfo> list = mapper.selectAll();
        list.forEach(System.out::println);
    }

    @Test void insertOne(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("zhaoliu");
        userInfo.setUserAge(21);
        int id = mapper.insertOne(userInfo);
        System.out.println("insert successfully, id=" + id);
    }

    @Test
    void updateAge(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        mapper.updateAge("lisi", 1000);
    }

    @Test
    void deleteByName(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        mapper.deleteByName("zhaoliu");
    }
}