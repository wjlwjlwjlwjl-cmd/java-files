package com.wjl.rag_demo.config;

import java.util.Arrays;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingModel;

@SuppressWarnings("null")
@Configuration
public class VectorStoreConf {
    @Bean
    public SimpleVectorStore getVectorStore(DashScopeEmbeddingModel dashScopeEmbeddingModel){
        Document d1 = Document.builder().text("2025年夏季奥运会将于巴黎举行, 预计吸引全球数百万观众").build();
        Document d2 = Document.builder().text("对比学习框架下多语言BERT模型的语义表示分析").build();
        Document d3 = Document.builder().text("暮色中的老槐树在风中摇曳, 枯枝划破绯红的晚霞").build();
        SimpleVectorStore vectorStore = SimpleVectorStore.builder(dashScopeEmbeddingModel).build();
        vectorStore.add(Arrays.asList(d1, d2, d3));
        return vectorStore;
    }
}
