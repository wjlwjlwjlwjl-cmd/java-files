package com.wjl.dao;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
    private HashMap<String, String> users = new HashMap<>();
    public boolean exists(String name){
        return users.containsKey(name);
    }

    public boolean verify(String name, String password){
        return exists(name) && users.get(name).equals(password);
    }

    public void newUser(String name, String password){
        users.put(name, password);
    }
}
