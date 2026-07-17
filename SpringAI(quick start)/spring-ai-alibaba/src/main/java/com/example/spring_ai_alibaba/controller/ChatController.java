package com.example.spring_ai_alibaba.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatClient alibabaChatClient;

    ChatController(ChatClient alibabaChatClient) {
        this.alibabaChatClient = alibabaChatClient;
    }

    //普通请求
    @RequestMapping(value = "/test", method=RequestMethod.GET)
    public String test(@RequestParam String param) {
        return alibabaChatClient.prompt().user(param).call().content();
    }
    
    //流式输出
    @RequestMapping(value = "/stream", produces = "text/html;charset=utf-8")
    public Flux<String> stream(String msg){
        return alibabaChatClient.prompt(msg).stream().content();
    }

    //修改请求参数
    @RequestMapping(value = "/system", produces = "text/html;charset=utf-8")
    public Flux<String> system(String role, String msg){
        return alibabaChatClient.prompt(msg).system(sp -> sp.param("word", role)).stream().content();
    }

    //记忆
    @RequestMapping(value = "/memory", produces = "text/html;charset=utf-8")
    public Flux<String> memory(String msg, String chatId){
        return alibabaChatClient.prompt().user(msg).advisors(spec -> spec.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)).stream().content();
    }
}
