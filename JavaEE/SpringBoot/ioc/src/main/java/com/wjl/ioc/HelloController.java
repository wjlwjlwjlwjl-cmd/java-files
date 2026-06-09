package com.wjl.ioc;

import org.springframework.stereotype.Component;

@Component
public class HelloController {
    public void print(){
        System.out.println("hello controller");
    }
}
