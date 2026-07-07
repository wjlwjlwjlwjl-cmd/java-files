package org.example.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {
    @Bean
    public ChatClient chatClient(ChatModel chatModel){
        return ChatClient.builder(chatModel)
                .defaultSystem("你叫打火机，用来打火") //系统提示词
                .defaultAdvisors(new SimpleLoggerAdvisor()) //打印日志，记录所有经过该advisor的请求和响应的状态
                .build();
    }
}
