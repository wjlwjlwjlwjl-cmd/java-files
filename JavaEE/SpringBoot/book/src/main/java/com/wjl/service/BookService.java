package com.wjl.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wjl.dao.BookDao;
import com.wjl.entity.BookInfo;

@Component
public class BookService {
    @Autowired
    private BookDao dao;
    public ArrayList<BookInfo> get_list(){
        dao.mockData();
        return dao.get_list();
    }
}
