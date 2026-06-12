package com.example.demo.mapper;

import com.example.demo.entity.MsgInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MsgMapper{
    @Insert("insert into msg_table(`from`, `to`, msg) values(#{from}, #{to}, #{msg})")
    public void publish(MsgInfo msgInfo);

    @Select("select*from msg_table")
    public List<MsgInfo> getList();
}