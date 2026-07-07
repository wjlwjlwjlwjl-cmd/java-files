package com.example.demo.controller;

import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/login")
    public boolean login(HttpServletRequest request, String username, String password){
        int ret = service.login(username, password);
        if(ret == 0){
            return false;
        }
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("username", username);
        httpSession.setAttribute("password", password);
        return true;
    }

    @Transactional
    @RequestMapping("/register")
    public boolean register(String username, String password){
        int ret = service.register(username, password);
        return ret != 1;
    }

    @RequestMapping("/check_login")
    public boolean checkLogin(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        return session != null;
    }

    @RequestMapping(value="self_introduction", produces = "application/json")
    public String selfIntroduction(){
        return "self introduction";
    }
}