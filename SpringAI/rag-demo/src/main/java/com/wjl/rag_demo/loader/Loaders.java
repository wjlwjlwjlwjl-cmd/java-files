package com.wjl.rag_demo.loader;

import java.util.List;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher.SummaryType;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingModel;
import com.alibaba.cloud.ai.reader.mysql.MySQLDocumentReader;
import com.alibaba.cloud.ai.reader.mysql.MySQLResource;

import redis.clients.jedis.JedisPooled;

@Component
public class Loaders {
    private ChatModel chatModel;
    private DashScopeEmbeddingModel dashScopeEmbeddingModel;

    @Value("classpath:/file/Json.json") 
    Resource resource;

    public Loaders(ChatModel chatModel, DashScopeEmbeddingModel dashScopeEmbeddingModel){
        this.chatModel = chatModel;
        this.dashScopeEmbeddingModel = dashScopeEmbeddingModel;
    }

    @SuppressWarnings("null")
    public RedisVectorStore loadJson(){ //Spring 的 Resource 对象
        //Extract，文本读取
        JsonReader jsonReader = new JsonReader(resource); //在构造时，可以再附加上字段名来取 Json 中的指定字段
        List<Document> documents = jsonReader.get();

        //Transform，文档处理
        //文档分割
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter(); //依据 token 进行文本切割
        documents = tokenTextSplitter.apply(documents);
        //文档关键字提取
        KeywordMetadataEnricher keywordMetadataEnricher = new KeywordMetadataEnricher(chatModel, 5);
        documents = keywordMetadataEnricher.apply(documents);
        //文档摘要生成，可以给上一篇、当前文档、下一篇文档都生成摘要，再拼接到文档中，帮助 LLM 理解文档内容和上下文
        SummaryMetadataEnricher summaryMetadataEnricher = new SummaryMetadataEnricher(chatModel, List.of(SummaryType.PREVIOUS, SummaryType.CURRENT, SummaryType.NEXT));
        documents = summaryMetadataEnricher.apply(documents);

        //Load，文档加载到向量数据库
        //SimpleVectorStore，直接在内存中存储
        //RedisVectorStore，在 Redis 中存储
        JedisPooled jedisPooled = new JedisPooled(); // Redis 连接配置
        RedisVectorStore redisVectorStore = RedisVectorStore.builder(jedisPooled, dashScopeEmbeddingModel).build();
        redisVectorStore.add(documents);

        return redisVectorStore;
    }

    public List<Document> loadTxt(@Value("classpath:/file/Text.txt") Resource resource){
        TextReader textReader = new TextReader(resource); //也可以通过 url 初始化
        textReader.getCustomMetadata().put("author", "bit");    //通过customMetaData可以获取到一个可修改的Map，可以增添键值对，全局注入到生成的 Document 中
        return textReader.get();
    }

    //加载html，JsoupDocumentReader；加载markdown，MarkdownDocumentLoader；加载 PDF，PagePdfDocumentReader；加载 docx、pptx、html 使用 TidaDocumentReader
    //对于各种类型的文件都可以阅读
    public List<Document> loadFile(@Value("classpath:/file/File.file") Resource resource){
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(resource);
        return tikaDocumentReader.get();
    }

    //从数据库中加载数据
    public List<Document> loadMysql(){
        List<String> columns = List.of("Host", "User", "Select_priv");
        MySQLResource mySQLResource = new MySQLResource("mysql", "select*from user limit 10", columns, columns);
        MySQLDocumentReader mySQLDocumentReader = new MySQLDocumentReader(mySQLResource);
        return mySQLDocumentReader.get();
    }
}