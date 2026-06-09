package com.wjl.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="captcha")
public class CaptchaConf {
    private int width;
    private int height;
    private int timeLimit;
    private long unit;
}
