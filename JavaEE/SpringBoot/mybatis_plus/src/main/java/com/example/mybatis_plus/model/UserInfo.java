package com.example.mybatis_plus.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
//@TableName()指定表名
public class UserInfo{
    @TableId("id") //指定主键
    private Integer id;
    private String userName;
    private String password;
    private Integer age;
    private Integer gender;
    private String phone;
    private Integer deleteFlag;
    private Date createDate;
    private Date updateDate;
}