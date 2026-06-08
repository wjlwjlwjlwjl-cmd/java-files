package com.wjl.entity;

import lombok.Data;

@Data
public class BookInfo {
    private String bookName;
    private String author;
    private int count;
    private double price;
    private String publish;
    private int status;
    private String statusCN;
}
