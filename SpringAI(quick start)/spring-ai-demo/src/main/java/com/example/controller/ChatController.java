package com.example.controller;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
@SuppressWarnings("null")
public class ChatController {
    private ChatClient chatClient;
    private ChatClient ollamChatClient;
    private record introduction(String name, List<String> ingredients, String taste){};
    private OpenAiApi openAiApi = new OpenAiApi("https://dashscope.aliyuncs.com/compatible-mode", "sk-a5f277ca38f8426ba23c68a313c6c46b");
    private OpenAiChatModel openAiChatModel = new OpenAiChatModel(openAiApi, new OpenAiChatOptions().builder().model("qwen-plus").build());

    public ChatController(ChatClient.Builder chatClient){
        this.chatClient = chatClient
            .defaultOptions(OpenAiChatOptions.builder().model("qwen-plus").build())
            .defaultSystem("你是一个厨师，我给你一道菜，介绍一下制作步骤")      //设置系统提示词
            .defaultAdvisors(new SimpleLoggerAdvisor())         //Advisor是介于用户请求和模型之间的组件，对用户提示进行过滤、增强等，思想借鉴了 Spring AOP
            .build();

        this.ollamChatClient = chatClient
            .defaultOptions(OpenAiChatOptions.builder().model("deepseek-r1:1.5b").build())
            .build();
    }

    @RequestMapping("/hello")
    public String hello(String msg){
        return chatClient.prompt()
            .user(msg)   //用户提示词
            .call()                         //调用模型
            .content();                     //获取正文
    }

    @RequestMapping("/dish")
    public String dish(String msg){
        return chatClient.prompt()
            .user(msg)
            .call()
            .entity(introduction.class)     //结构化输出
            .toString();
    }

    @RequestMapping(value = "/stream", produces = "text/html;charset=utf-8")
    public Flux<String> stream(String msg){
        return chatClient.prompt()
            .user(msg)
            .stream()       //流失输出，返回 Flux<String>
            .content();
    }

    @RequestMapping("/role")    //设定角色身份
    public String call(String msg){
        SystemMessage systemMessage = new SystemMessage("你是一个英国人，只会说英语");
        UserMessage userMessage =  new UserMessage(msg);
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        return openAiChatModel.call(prompt).getResult().getOutput().getText();
    }

    @RequestMapping(value = "/stream2", produces = "text/html;charset=utf-8")
    public Flux<String> stream2(String msg){
        Flux<ChatResponse> flux = openAiChatModel.stream(new Prompt(msg));
        return flux.map(s -> s.getResult().getOutput().getText());
    }

    //ChatModel与ChatClient对比
    //ChatModel 更偏底层，能够自定义与大模型交互的参数配置与相应细节，灵活性更高
    //ChatClient 对 ChatModel 进行了封装，自动集成了提示词管理、结构化输出、链式 API、Advisor 功能
}
