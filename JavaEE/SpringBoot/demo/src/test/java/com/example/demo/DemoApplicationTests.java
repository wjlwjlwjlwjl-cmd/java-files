package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class DemoApplicationTests {
  public static void main(String[] args) {

  }

  @Test
  public void testJson() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    UserInfo userInfo = new UserInfo("wjl", 19, "123456");
    String s = objectMapper.writeValueAsString(userInfo);
    System.out.println(s);
  }

  @Test
  public void testJson2() throws JsonProcessingException {
    String s = "\"name\":\"wjl\", \"age\": 19, \"password\": \"123456\"";
    ObjectMapper objectMapper = new ObjectMapper();
    UserInfo userInfo = objectMapper.readValue(s, UserInfo.class);
    System.out.println(userInfo);
  }
}
