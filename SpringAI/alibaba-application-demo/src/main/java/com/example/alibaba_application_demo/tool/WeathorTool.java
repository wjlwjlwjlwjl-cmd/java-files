package com.example.alibaba_application_demo.tool;

public class WeathorTool {
    public String getWeathor(String msg){
        if(msg.equals("广州")){
            return "广州今日天气：暴雨";
        }
        else if(msg.equals("上海")){
            return "上海今日天气：晴天";
        }
        else{
            return "未知地点天气";
        }
    }
}
