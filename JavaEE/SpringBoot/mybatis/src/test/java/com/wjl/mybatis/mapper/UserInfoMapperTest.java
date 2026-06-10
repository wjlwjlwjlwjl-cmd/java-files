//  ./mvnw test -Dtest=UserInfoMapperTest#contextLoads2
//-Dtest指定运行哪个测试类，#contextLoads2 表示运行哪个测试方法
package com.wjl.mybatis.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wjl.mybatis.entity.UserInfo;

@SpringBootTest //加载 Spring 运行环境,比如需要注入依赖

public class UserInfoMapperTest {
    @BeforeEach //在执行每个用例前执行
    void setUp(){
        System.out.println();
        System.out.println("test begin");
    }

    @AfterEach //在执行每个用例后执行
    void tearDown(){
        System.out.println();
        System.out.println("test end");
    }

    @Autowired
    private org.springframework.context.ApplicationContext context; //这里就需要注入依赖

    @Test
    void selectAll(){
        UserInfoMapper bean = context.getBean(UserInfoMapper.class);
        bean.selectAll().stream().forEach(x -> System.out.println(x));
    }

    @Test
    void selectAllByName(){
        UserInfoMapper bean = context.getBean(UserInfoMapper.class);
        System.out.println(bean.selectAllByName("zhangsan"));
    }

    @Test
    void selectAllByNameAndAge(){
        UserInfoMapper bean = context.getBean(UserInfoMapper.class);
        System.out.println(bean.selectAllByNameAndAge("lisi", 19));
    }

    @Test
    void insertOne(){
        UserInfoMapper bean = context.getBean(UserInfoMapper.class);
        UserInfo user_info = new UserInfo();
        user_info.setUserAge(21);
        user_info.setUserName("zhaoliu");
        bean.insertOne(user_info);
    }

    @Test
    void deleteByName(){
        UserInfoMapper bean = context.getBean(UserInfoMapper.class);
        bean.deleteByName("wangwu");
    }
    @Test 
    void updateAge(){
        UserInfoMapper bean = context.getBean(UserInfoMapper.class);
        bean.updateAge("tianqi", 18);
    }
}
