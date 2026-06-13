package com.wjl.interrupter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;

@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession session = request.getSession(false);
        if(session == null){
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(401);
            String msg = "用户未登录";
            response.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));
            log.info("用户登录失败");
            return false;
        }
        log.info("用户登录成功");
        return true;
    }
}