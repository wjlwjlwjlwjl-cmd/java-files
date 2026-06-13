package com.example.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatis_plus.model.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Random;

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
        Random random = new Random();
        userInfo.setUserName("User" + random.nextInt());
        userInfo.setPassword(random.nextInt() + "");
        mapper.insert(userInfo);
    }

    @Test
    void mock(){
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        for(int i = 0; i < 15; i++){
            insertOne();
        }
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

    //QueryWrapper，起到where的作用，只要有where的地方都可以使用 wrapper
    @Test
    public void selectByCondition(){
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        //wrapper.select("id, user_name, age, password").eq("password", 9388492).like("user_name", "43");
        wrapper.lambda().select(UserInfo::getUserName, UserInfo::getPassword, UserInfo::getAge).eq(UserInfo::getPassword, 9388492).like(UserInfo::getUserName, "43");
        mapper.selectList(wrapper).forEach(x->System.out.println(x));
    }

    @Test
    public void updateByCondition(){
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.gt("id", 0);
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(new Random().nextInt(100));
        mapper.update(userInfo, wrapper);
    }

    @Test
    public void deleteByCondition(){
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 728326145);
        mapper.delete(wrapper);
    }

    @Test
    public void updateByCondition2(){
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<>();
        wrapper.set("user_name", "xxxxxx").in("id", List.of(728326145, 753491970));
        mapper.update(wrapper);
    }

    @Test
    void selectById1() {
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        UserInfo userInfo = mapper.selectById1(103374850);
        System.out.println(userInfo);
    }

    @Test
    void selectById2() {
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        List<UserInfo> userInfos = mapper.selectById2(103374850);
        System.out.println(userInfos);
    }

    @Test
    void selectByCondition1() {
        UserInfoMapper mapper = context.getBean(UserInfoMapper.class);
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 1);
        List<UserInfo> userInfos = mapper.selectByCondition1(wrapper);
        System.out.println(userInfos);
    }
}