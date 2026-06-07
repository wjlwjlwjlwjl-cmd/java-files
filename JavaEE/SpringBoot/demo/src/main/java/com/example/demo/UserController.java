package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user") //类路径，既支持get，又支持post
@RestController 
public class UserController{
    //@RequestMapping(value="/sayHi", method={RequestMapping.GET, RequestMapping.POST})
    @RequestMapping("/sayHi") //方法路径
    public String sayHi(){
        return "Hello SpringBoot";
    }

    //@GetMapping("/m2")
    @RequestMapping(value="/m2", method=RequestMethod.GET)
    public String m2(){
        return "method m2";
    }

    //@PostMapping("/m3")
    @RequestMapping(value="m3", method=RequestMethod.POST)
    public String m3(){
        return "method m2";
    }
}
