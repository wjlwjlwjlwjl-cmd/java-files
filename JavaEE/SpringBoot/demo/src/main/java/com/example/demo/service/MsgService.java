package com.example.demo.service;

import com.example.demo.entity.MsgInfo;
import com.example.demo.mapper.MsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgService{
    @Autowired
    private ApplicationContext context;

    public void publish(MsgInfo msgInfo){
        MsgMapper mapper = context.getBean(MsgMapper.class);
        mapper.publish(msgInfo);
    }

    public List<MsgInfo> getList(){
        MsgMapper mapper = context.getBean(MsgMapper.class);
        return mapper.getList();
    }
}