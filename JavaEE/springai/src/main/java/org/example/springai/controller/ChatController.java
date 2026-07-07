package org.example.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ChatClient chatClient;

    @RequestMapping(value = "/test", produces = "text/html;charset=utf-8")
    public String test(String message){
        return chatClient.prompt().user(message).call().content();
    }

    record Recipe(String dish, List<String> gradients){}

    //结构化输出
    @RequestMapping(value="/dish", produces="text/html;charset=utf-8")
    public String dish(String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(Recipe.class)
                .toString();
    }

    //流式输出
    @RequestMapping(value="/stream", produces = "text/html;charset=utf-8")
    public Flux<String> stream(String message){
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}
