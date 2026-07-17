package com.example.alibaba_application_demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Time;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private ChatClient chatClient;
    
    private final DashScopeImageModel imageModel;

    private final DashScopeSpeechSynthesisModel dashScopeSpeechSynthesisModel;

    public ChatController(ChatClient.Builder builder, DashScopeImageModel imageModel, DashScopeSpeechSynthesisModel dashScopeSpeechSynthesisModel){
        this.chatClient = builder.build();
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
        SpeechSynthesisResponse response =  dashScopeSpeechSynthesisModel.call(new SpeechSynthesisPrompt("胡艺城别卷了"));        
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
}
