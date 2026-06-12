package com.wjl.service;

import java.util.List;
import com.wjl.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjl.entity.BookInfo;

@Service
public class BookService {
    @Autowired
    private BookMapper mapper;

    public List<BookInfo> getList(){
        return mapper.getList();
    }

    public void insert(BookInfo bookInfo){
        mapper.insert(bookInfo);
    }

    public List<BookInfo> getPage(int offset, int pageSize){
        return mapper.getPage(offset, pageSize);
    }

    public void deleteByBookName(String bookName){
        mapper.deleteByBookName(bookName);
    }

    public void updateById(Integer id, String bookName, String author, Integer count, Double price, String publish, Integer status, String statusCN){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(id);
        bookInfo.setBookName(bookName);
        bookInfo.setPublish(publish);
        bookInfo.setAuthor(author);
        bookInfo.setPrice(price);
        bookInfo.setCount(count);
        bookInfo.setStatus(status);
        bookInfo.setStatusCN(statusCN);
        mapper.updateById(bookInfo);
    }
}
