package com.wjl.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjl.entity.BookInfo;
import com.wjl.service.BookService;

@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    BookService bs;

    @RequestMapping("/get_list")
    public ArrayList<BookInfo> get_list(){
        return bs.get_list();
    }
}
