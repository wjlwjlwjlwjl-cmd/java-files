package com.wjl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjl.service.UserService;

@RequestMapping("/user")
//@Controller 类注解，表示该类中的所有方法都返回页面
@RestController
public class UserController {
    /*
    * IoC(Inversion of Control)
    * 把对象交给Spring管理，而不是自己通过 new 创建
    * IOC中装的就是对象，叫做 Bean
    */

    @Autowired //注解表示取对象
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
