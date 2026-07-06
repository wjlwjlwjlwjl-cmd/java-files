package com.example.demo.config;

import com.example.demo.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice{
    @ExceptionHandler
    public Result<Exception> handle(Exception e){
        return Result.error(1, "内部错误", e);
    }
}