package com.example.alibaba_application_demo.tool;

import java.time.LocalDateTime;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTimeTool {
    //name 默认是方法名，description 工具描述，returnDirect 是否直接返回
    //参数默认是必选的，使用 @Nullable 可选；@ToolParam required 标记是否必选，description 描述参数
    @Tool(description = "get user's local time and zone"/*, returnDirect = true*/)
    public String dateTime(){
        return LocalDateTime.now(LocaleContextHolder.getTimeZone().toZoneId()).toString();
        //return "现在是2040年1月1日";
    }
}
