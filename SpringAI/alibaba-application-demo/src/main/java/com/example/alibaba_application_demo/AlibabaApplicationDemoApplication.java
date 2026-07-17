package com.example.alibaba_application_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AlibabaApplicationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlibabaApplicationDemoApplication.class, args);
		log.info("[AlibabaApplicationDemoApplication started]");
	}

}
