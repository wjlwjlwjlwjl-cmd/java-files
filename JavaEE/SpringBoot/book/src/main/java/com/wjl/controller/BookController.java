package com.wjl.controller;

import java.util.List;
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
    public List<BookInfo> get_list(){
        return bs.getList();
    }

    @RequestMapping("/insert")
    public void insert(String bookName, String author, int count, double price, String publish, int status, String statusCN){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName(bookName);
        bookInfo.setPublish(publish);
        bookInfo.setAuthor(author);
        bookInfo.setPrice(price);
        bookInfo.setCount(count);
        bookInfo.setStatus(status);
        bookInfo.setStatusCN(statusCN);
        bs.insert(bookInfo);
    }

    @RequestMapping("get_page")
    public List<BookInfo> get_page(int offset, int pageSize){
        return bs.getPage(offset, pageSize);
    }

    @RequestMapping("delete_by_book_name")
    public void deleteByBookName(String bookName) {
        bs.deleteByBookName(bookName);
    }

    @RequestMapping("/insert_mock")
    public void insertMock(){
        for(int i = 0; i < 20; i++){
            BookInfo bookInfo = new BookInfo();
            bookInfo.setBookName("book" + i);
            bookInfo.setPublish("publish" + i);
            bookInfo.setAuthor("author" + i);
            bookInfo.setPrice(i * 1.00);
            bookInfo.setCount(i);
            bookInfo.setStatus(i % 2);
            bookInfo.setStatusCN("statusCN" + i);
            bs.insert(bookInfo);
        }
    }

    @RequestMapping("update_by_id")
    public void updateById(Integer id, String bookName, String author, Integer count, Double price, String publish, Integer status, String statusCN){
        if(id == null){
            return;
        }
        bs.updateById(id, bookName, author, count, price, publish, status, statusCN);
    }
}
