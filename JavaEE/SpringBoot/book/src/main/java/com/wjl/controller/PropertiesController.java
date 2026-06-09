package com.wjl.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/properties")
@RestController
public class PropertiesController {
    @Value("${my.key1}")
    private String mykey;

    @Value("${my.key2}")
    private String mykey2;

    @Autowired
    private Person person;

    @Autowired
    private InfoList infoList;

    @RequestMapping("/read")
    public String readProperties(){
        return "get my.key:" + mykey + " " + mykey2;
    }

    @RequestMapping("get_person")
    public String get_person(){
        return person.toString();
    }

    @RequestMapping("get_list")
    public InfoList get_list(){
        return infoList;
    }
}

@Component
@ConfigurationProperties(prefix="list")
class InfoList{
    public ArrayList<String> arr;
}

@Component
@ConfigurationProperties(prefix="person")
class Person{
    private String name;
    private int age;

    public String toString(){
        return name + ": " + age;
    }
}