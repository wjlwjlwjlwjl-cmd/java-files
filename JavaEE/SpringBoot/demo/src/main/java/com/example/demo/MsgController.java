package com.example.demo;

import java.util.ArrayList;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.classes.MsgInfo;

@RequestMapping("/msg")
@RestController
public class MsgController {
    private static ArrayList<MsgInfo> list = new ArrayList<>();

    @RequestMapping("/publish")
    public String publish(String from, String to, String msg) {
        if (!StringUtils.hasLength(from) || !StringUtils.hasLength(to) || !StringUtils.hasLength(msg)) {
            return "{\"ok\": 0}";
        }
        MsgInfo unit = new MsgInfo(from, to, msg);
        list.add(unit);
        System.out.println(unit.getFrom());
        return "{\"ok\": 1}";
    }

    @RequestMapping("/get_list")
    public ArrayList<MsgInfo> getList() {
        return list;
    }
}
