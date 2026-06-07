package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/calc")
@RestController
public class CalcController {
    @RequestMapping("/sum")
    public String sum(Integer val1, Integer val2) {
        if (val1 == null || val2 == null) {
            return "不合法的参数请求";
        }
        return (val1 + val2) + "";
    }
}
