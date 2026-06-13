package com.wjl.config;

import com.wjl.interrupter.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        UserInterceptor interceptor = new UserInterceptor();
        registry.addInterceptor(interceptor)
                .addPathPatterns("/book/**")
                .excludePathPatterns("/user/**");
    }
}