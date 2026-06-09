package com.wjl.book;

import java.util.Scanner;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;

public class Captcha{
    public static void main(String[] args) {
        LineCaptcha lineCaptcha = new LineCaptcha(200, 100);
        lineCaptcha.write("/home/diinki/图片");
        Console.log(lineCaptcha.getCode());
        Scanner scanner = new Scanner(System.in);
        System.out.println("请进行验证 >> ");
        String op = scanner.nextLine();
        boolean ret = lineCaptcha.verify(op);
        if(ret){
            System.out.println("success");
        }
        else{
            System.out.println("fail");
        }
        scanner.close();
    }
}