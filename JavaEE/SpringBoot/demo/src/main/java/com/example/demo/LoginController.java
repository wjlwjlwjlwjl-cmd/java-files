package com.example.demo;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {
    @RequestMapping("/login")
    public boolean login(String name, String password, HttpServletRequest request) {
        if (!StringUtils.hasLength(name) || !StringUtils.hasLength(password)) {
            return false;
        }
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("password", password);
        System.out.println(name + " " + password);
        return true;
    }

    @RequestMapping("/get_login")
    public String getLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "user not login";
        }
        String name = (String) session.getAttribute("name");
        return name;
    }
}
