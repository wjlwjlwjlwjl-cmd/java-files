package com.wjl.controller;

import java.io.IOException;
import java.lang.System.Logger;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import com.wjl.entity.CaptchaConf;

@Slf4j //省去手动定义logger的代码
@RequestMapping("/verify_code")
@RestController
public class CaptchaController {
    @Autowired
    private CaptchaConf captchaConf;

    org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    @RequestMapping("/get")
    public void get(HttpSession session, HttpServletResponse response){
        response.setContentType("image/png");
        try{
            int width = captchaConf.getWidth();
            int height = captchaConf.getHeight();
            ICaptcha iCaptcha = CaptchaUtil.createLineCaptcha(width, height);
            iCaptcha.createCode();
            iCaptcha.write(response.getOutputStream());
            session.setAttribute("Date", new Date());
            session.setAttribute("Code", iCaptcha.getCode());
            logger.info("handle a request");
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    @RequestMapping("/check")
    public int check(HttpSession session, String code){
        Date time = (Date)session.getAttribute("Date");
        Date ret = new Date(System.currentTimeMillis() - captchaConf.getTimeLimit() * captchaConf.getUnit());
        if(!StringUtils.hasLength(code)){
            return 1;
        }
        if(ret.compareTo(time) > 0){
            return 2;
        }
        String answer = (String)session.getAttribute("Code");
        if(!answer.equalsIgnoreCase(code)){
            return 3;
        }
        return 0;
    }
}
