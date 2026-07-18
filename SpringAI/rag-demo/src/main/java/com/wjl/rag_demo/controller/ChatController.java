package com.wjl.rag_demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.cloud.ai.advisor.RetrievalRerankAdvisor;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingModel;
import com.alibaba.cloud.ai.dashscope.rerank.DashScopeRerankModel;
import com.wjl.rag_demo.loader.Loaders;


@RestController
@RequestMapping("/chat")
@SuppressWarnings({"null"})
public class ChatController {
    private final DashScopeEmbeddingModel dashScopeEmbeddingModel;

    private final SimpleVectorStore simpleVectorStore;

    private final ChatClient chatClient;

    private final DashScopeChatModel dashScopeChatModel;

    private final DashScopeRerankModel dashScopeRerankModel;

    public ChatController(ChatClient.Builder builder, DashScopeChatModel dashScopeChatModel, DashScopeEmbeddingModel dashScopeEmbeddingModel, SimpleVectorStore simpleVectorStore, DashScopeRerankModel dashScopeRerankModel){
        this.chatClient = builder.build();
        this.dashScopeEmbeddingModel = dashScopeEmbeddingModel;
        this.simpleVectorStore = simpleVectorStore;
        this.dashScopeChatModel = dashScopeChatModel;
        this.dashScopeRerankModel = dashScopeRerankModel;
    }

    @RequestMapping("/test")
    public void test(){
        float[] ret = dashScopeEmbeddingModel.embed("Lenevo ThinkBook 16+");
        System.out.println(ret.length);
        System.out.println(Arrays.toString(ret));
    }

    @RequestMapping("/memory")
    public void memory(){
        SearchRequest searchRequest = SearchRequest.builder().query("傍晚风景").topK(1).similarityThreshold(0.3).build();
        List<Document> rets = simpleVectorStore.similaritySearch(searchRequest);
        System.out.println(rets);
    }

    //在已经将文档加载到向量数据库的情况下，可以直接使用 QuestionAnswerAdvisor 完成 RAG，同时 QuestionAnswerAdvisor 具有默认的提示词模板，包含 <query> 和 <question_answer_context> 两个占位符，可以通过 .promptTemplate 自定义
    @RequestMapping(value = "/rag1", produces = "text/html;charset=utf-8")
    public String rag1(String msg){
        SearchRequest searchRequest = SearchRequest.builder()
            .similarityThreshold(0.3)
            .query(msg)
            .topK(1)
            .build();
        QuestionAnswerAdvisor questionAnswerAdvisor = QuestionAnswerAdvisor.builder(simpleVectorStore).searchRequest(searchRequest).build();

        return chatClient.prompt()
            .user(msg)
            .advisors(questionAnswerAdvisor)
            .call()
            .content();
    }

    /// ETL, Extract（加载数据）, Transform（处理、清洗数据）, Load（加载数据到向量数据库），数据准备阶段
    /// 在数据准备阶段后，如果想要实现高质量的 RAG，还需要进行重排序。语义上相关不代表就是最佳的匹配结果
    public String reRank(String msg){
        Loaders loaders = new Loaders(dashScopeChatModel, dashScopeEmbeddingModel);
        RedisVectorStore redisVectorStore = loaders.loadJson(); //读取、处理、加载到向量数据库
        RetrievalRerankAdvisor rerankAdvisor = new RetrievalRerankAdvisor(redisVectorStore, dashScopeRerankModel, SearchRequest.builder().topK(200).build());
        return chatClient.prompt()
            .user(msg)
            .advisors(rerankAdvisor)
            .call()
            .content();
    }

    //需要使用数据库持久化，可以使用 JdbcChatMemoryRepository
}
