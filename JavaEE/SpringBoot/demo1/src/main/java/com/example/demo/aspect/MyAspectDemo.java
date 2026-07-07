package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class MyAspectDemo {
    @Around("@annotation(com.example.demo.aspect.MyAspect)")
    public void myAspectFunc(){
        log.info("自定义类实现 AOP 通知方法执行");
    }
}
