package com.example.alibaba_application_demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Time;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import com.example.alibaba_application_demo.tool.DateTimeTool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatClient chatClient;

    private final ChatModel chatModel;
    
    private final DashScopeImageModel imageModel;

    private final DashScopeSpeechSynthesisModel dashScopeSpeechSynthesisModel;

    public ChatController(ChatClient.Builder builder, ChatModel chatModel, DashScopeImageModel imageModel, DashScopeSpeechSynthesisModel dashScopeSpeechSynthesisModel){
        this.chatModel = new DashScopeChatModel(new DashScopeApi(System.getenv("DASHSCOPE_API_KEY")));
        this.chatClient = builder.defaultTools(new DateTimeTool()).build(); //默认工具
        this.imageModel = imageModel;
        this.dashScopeSpeechSynthesisModel = dashScopeSpeechSynthesisModel;
    }

    @RequestMapping("/test")
    public String test(){
        return chatClient.prompt().user("who are you").call().content();
    }

    ////////////////////// 图片生成 //////////////////////////

    //生成基础图片
    @RequestMapping("/image/test")
    public String image(){
        ImageResponse imageResponse = imageModel.call(new ImagePrompt("孩子在海边玩耍"));
        return imageResponse.getResult().getOutput().getUrl();
    }

    //生成卡通图片
    @RequestMapping("/image/cartoon")
    public String cartoon(){
        ImageOptions imageOptions = DashScopeImageOptions.builder().withStyle("<anime>").build();
        ImageResponse imageResponse = imageModel.call(new ImagePrompt("孩子在海边玩耍", imageOptions));
        return imageResponse.getResult().getOutput().getUrl();
    }

    ////////////////////// 语音生成 //////////////////////////
    @RequestMapping("/voice/test")
    public void voiceTest() throws IOException{
        File output = new File(System.getProperty("user.dir") + "/output-" + new Time(0) + ".mp3");
        SpeechSynthesisResponse response =  dashScopeSpeechSynthesisModel.call(new SpeechSynthesisPrompt("胡艺城不要学俄语、往代码里加优化空间了"));        
        try(FileOutputStream fileOutputStream = new FileOutputStream(output)){
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fileOutputStream.write(byteBuffer.array());
        }
        catch(IOException e){
            throw new IOException(e.getMessage());
        }
    }

    @RequestMapping("/voice/slowed")
    public void voiceSlowed() throws IOException{
        File output = new File(System.getProperty("user.dir") + "/output-" + new Time(0) + ".mp3");
        DashScopeSpeechSynthesisOptions speechSynthesisOptions = DashScopeSpeechSynthesisOptions.builder().withSpeed(0.25).build();
        SpeechSynthesisResponse speechSynthesisResponse = dashScopeSpeechSynthesisModel.call(new SpeechSynthesisPrompt("胡艺城别卷了", speechSynthesisOptions));
        try(FileOutputStream fos = new FileOutputStream(output)){
            ByteBuffer buffer = speechSynthesisResponse.getResult().getOutput().getAudio();
            fos.write(buffer.array());
        }
        catch(IOException e){
            throw new IOException(e.getMessage());
        }
    }

    ////////////////////// Function Calling //////////////////////////
    /// 注册工具：name、description、input schema
    /// 模型决策：决定是否调用工具，如果调用，会返回工具调用请求，并附带相应参数
    /// 工具调用 返回结果，llm 根据工具调用结果整理出回答
    
    // 为 ChatClient 添加工具
    @RequestMapping("/tool/test1")
    public String toolTest1(String msg){
        return chatClient.prompt()
            .user(msg)
            .tools(new DateTimeTool()) //运行时工具
            .call()
            .content();
        //return chatClient.prompt().user(msg).call().content();
    }

    // 为 ChatModel 添加工具
    @RequestMapping("/tool/test2")
    public String toolTest2(String msg){
        ToolCallback[] toolCallbacks = ToolCallbacks.from(new DateTimeTool());
        ChatOptions chatOptions = ToolCallingChatOptions.builder().toolCallbacks(toolCallbacks).build();
        Prompt prompt = new Prompt(msg, chatOptions);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}