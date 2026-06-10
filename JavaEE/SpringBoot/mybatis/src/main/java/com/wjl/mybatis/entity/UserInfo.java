package com.wjl.mybatis.entity;

import lombok.Data;

//数据库中，字段要求按照蛇形命名法；java中要求对象的成员变量使用小驼峰
//在查询的时候，直接使用java的类来接受就会出现命名不匹配的问题（即使mybatis不区分大小写）
//所以，把两种命名进行转换，有两种方法
//1. 其别名，select user_name as userName
//2. 结果映射，@Results @Result @ResultMap（复用其他方法的映射）
//3. 配置文件开启转换，map-underscore-to-camel-case

@Data
public class UserInfo {
    private int id;
    private String userName;
    private Integer userAge;
}

//表现层 Controller、业务逻辑层 Service、数据层 Dao
//MVC Model View Controller