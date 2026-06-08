package com.wjl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjl.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService us;

    @RequestMapping("/register")
    public String register(String name, String password){
        int op = us.register(name, password);
        if(op == 2){
            return "{\"ok\": 2}";
        }
        if(op == 1){
            return "{\"ok\": 1}";
        }
        return "{ \"ok\": 0 }";
    }

    @RequestMapping("/login")
    public String login(String name, String password){
        int op = us.login(name, password);
        if(op == 2){
            return "{\"ok\": 2}";
        }
        if(op == 1){
            return "{\"ok\": 1}";
        }
        return "{ \"ok\": 0 }";
    }
}
