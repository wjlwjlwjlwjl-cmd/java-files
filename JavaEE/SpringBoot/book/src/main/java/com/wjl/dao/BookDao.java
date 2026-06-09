package com.wjl.dao;

import java.util.ArrayList;
import com.wjl.entity.BookInfo;
import java.util.Random;

import org.springframework.stereotype.Repository;

@Repository
//@Component //将这个类交给SpringBoot来管理，需要是直接从SpringBoot获取即可（使用@Autowire)
public class BookDao {
    private ArrayList<BookInfo> books = new ArrayList<>();

    //造假数据来测试
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