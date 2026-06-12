package com.wjl.mapper;

import com.wjl.entity.BookInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper{
    @Select("select*from book_table")
    List<BookInfo> getList();

    @Insert("insert into book_table(book_name, author, count, price, publish, status, statusCN) values(#{bookName}, #{author}, #{count}, #{price}, #{publish}, #{status}, #{statusCN})")
    void insert(BookInfo bookInfo);

    @Select("select*from book_table order by id asc limit #{offset}, #{pageSize}")
    List<BookInfo> getPage(int offset, int pageSize);

    @Delete("delete from book_table where book_name=#{bookName}")
    void deleteByBookName(String bookName);

    void updateById(BookInfo bookInfo);
}