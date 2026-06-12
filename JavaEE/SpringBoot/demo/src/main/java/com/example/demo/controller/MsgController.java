package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MsgInfo;

@RequestMapping("/msg")
@RestController
public class MsgController {

    @Autowired
    private MsgService msgService;

    @RequestMapping("/publish")
    public String publish(String from, String to, String msg) {
        if (!StringUtils.hasLength(from) || !StringUtils.hasLength(to) || !StringUtils.hasLength(msg)) {
            return "{\"ok\": 0}";
        }
        MsgInfo item = new MsgInfo();
        item.setFrom(from);
        item.setTo(to);
        item.setMsg(msg);
        msgService.publish(item);

        return "{\"ok\": 1}";
    }

    @RequestMapping("/get_list")
    public List<MsgInfo> getList() {
        return msgService.getList();
    }
}
