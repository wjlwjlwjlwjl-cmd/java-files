package com.example.demo.entity;

import lombok.Data;

@Data
public class Result<T> {
    private int errCode;
    private String errmsg;
    private T data;

    public static<T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.data = data;
        result.errCode = 0;
        result.errmsg = "";
        return result;
    }

    public static<T> Result<T> error(int errCode, String errmsg, T data){
        Result<T> result = new Result<>();
        result.errCode = errCode;
        result.errmsg = errmsg;
        result.data = data;
        return result;
    }
}