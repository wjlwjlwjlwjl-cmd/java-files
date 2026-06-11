package com.wjl.mybatis.mapper;

import com.wjl.mybatis.entity.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
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

    @Test
    void sqlInjection(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        List<UserInfo> rets = mapper.sqlInjection("zhangsan", "xxx' or 1='1");
        if(!rets.isEmpty()){
            System.out.println("log successfully");
        }
        else{
            System.out.println("log failed");
        }
    }

    @Test
    void insertIf(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        mapper.insertIf(null, null, null);
    }

    @Test
    void selectByCondition(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        List<UserInfo> rets = mapper.selectByCondition(null, null, null);
        rets.stream().forEach(x->System.out.println(x));
    }

    @Test
    void updateByCondition(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        mapper.updateByCondition("wujiu", 1000, null);
    }

    @Test
    public void selectForEach(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<UserInfo> users = mapper.selectForEach(ids);
        users.stream().forEach(x->System.out.println(x));
    }

    @Test
    public void selectBySql(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        List<UserInfo> rets = mapper.selectBySql();
        rets.stream().forEach(x->System.out.println(x));
    }

    @Test
    public void insertForEach(){
        UserInfoMapperXML mapper = context.getBean(UserInfoMapperXML.class);
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        UserInfo user1 = new UserInfo();
        user1.setUserName("1");
        user1.setId(100);
        user1.setUserAge(100);
        user1.setPassWord("1122");
        UserInfo user2 = new UserInfo();
        user2.setUserName("2");
        user2.setId(101);
        user2.setUserAge(100);
        user2.setPassWord("1122");

        userInfos.add(user1);
        userInfos.add(user2);
        mapper.insertForEach(userInfos);
    }
}