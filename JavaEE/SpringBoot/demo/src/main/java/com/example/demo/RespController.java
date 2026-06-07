package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/getPage")
@Controller // @Controller是类注解，表示这个类所有方法都返回页面
// @RestControler是类注解，表示该类的所有方法都返回数据
// @ResponseBody 既是方法注解，又是类注解。修饰方法时，表示该方法返回数据；修饰类时，表示该类所有方法返回数据
public class RespController {
    @RequestMapping("/index")
    public String getIndex() {
        return "/index.html";
    }

    // 返回类对象自动转换为Json
    @ResponseBody
    @RequestMapping(value = "/userinfo", headers = "User-Agent")
    public UserInfo userinfo(HttpServletResponse response) {
        response.setStatus(200); // 设置状态码
        response.setHeader("Server-Agent", "CachyOS x86_64");
        UserInfo us = new UserInfo("wjl", 19, "123456");
        System.out.println(us);
        return us;
    }
}
