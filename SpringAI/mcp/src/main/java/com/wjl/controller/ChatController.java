package com.wjl.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mcp")
@RestController
public class ChatController {
    private final ChatClient chatClient;

    //添加mcp工具到ChatClient，同时需要在application.yaml中配置mcp配置
    public ChatController(ChatClient.Builder builder, ToolCallbackProvider toolCallbackProvider){
        this.chatClient = builder.defaultToolCallbacks(toolCallbackProvider).build();
    }

    @RequestMapping("/open")
    public String open(){
        return chatClient.prompt().user("帮我打开浏览器，用百度搜索今日天气").call().content();
    }

    //自定义 MCP
    //1. 定义一个工具 @Tool 声明式定义或者通过反射编程式定义
    //2. @Bean 通过 ToolCallBackProvider 交由 Spring 管理
    //3. 打包成 jar 文件，粗暴的方式直接本地调用 jar 包即可，或者也可以上传远端部署
}
