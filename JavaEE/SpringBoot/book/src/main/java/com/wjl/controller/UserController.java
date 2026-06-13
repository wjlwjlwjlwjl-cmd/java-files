package com.wjl.controller;

import com.wjl.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService us;

    @RequestMapping("/login")
    public int login(HttpServletRequest request, String userName, String userPassword){
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        session.setAttribute("userPassword", userPassword);
        return us.login(userName, userPassword);
    }

    @RequestMapping("/register")
    public int register(HttpServletRequest request, String userName, String userPassword){
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        session.setAttribute("userPassword", userPassword);
        return us.register(userName, userPassword);
    }
}