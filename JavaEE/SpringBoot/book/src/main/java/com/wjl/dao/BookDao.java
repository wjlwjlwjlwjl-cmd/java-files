package com.wjl.dao;

import java.util.ArrayList;
import com.wjl.entity.BookInfo;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class BookDao {
    private ArrayList<BookInfo> books = new ArrayList<>();

    public void mockData(){
        Random random = new Random();
        for(int i = 1; i < 15; i++){
            BookInfo book = new BookInfo();
            book.setBookName("Book" + i);
            book.setAuthor("Name" + i);
            book.setCount(i);
            book.setPrice(random.nextDouble(500));
            book.setPublish("Publish" + i);
            book.setStatus(i % 2);
            book.setStatusCN("statusCN" + i);
            books.add(book);
        }
    }

    public ArrayList<BookInfo> get_list(){
        return books;
    }
}
