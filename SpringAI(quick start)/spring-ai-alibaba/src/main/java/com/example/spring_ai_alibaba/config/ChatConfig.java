package com.example.spring_ai_alibaba.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {
    @Bean
    public ChatMemory chatMemory(){
        return new InMemoryChatMemory();
    }

    @Bean 
    public ChatClient chatClient(ChatClient.Builder chatClient, ChatMemory chatMemory){
        return chatClient.defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory)).build();
    }
}