package com.wjl.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mcp")
@RestController
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder, ToolCallbackProvider toolCallbackProvider){
        this.chatClient = builder.defaultToolCallbacks(toolCallbackProvider).build();
    }

    @RequestMapping("/train")
    public String train(String msg){
        return chatClient.prompt().user(msg).call().content();
    }
}
