package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MsgInfo {
    private int id;
    private String from;
    private String to;
    private String msg;
    private Date createTime;
    private Date updateTime;
    private boolean deleteFlag;
}
