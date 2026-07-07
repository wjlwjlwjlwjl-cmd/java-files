package com.example.demo.controller;

import com.example.demo.aspect.MyAspect;
import com.example.demo.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager; //JDBC 事务管理器
    @Autowired
    TransactionDefinition transactionDefinition; //事务定义

    @MyAspect
    @RequestMapping("/t1")
    public Result<?> t1(){
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        log.info("数据库事务操作");
        dataSourceTransactionManager.commit(transactionStatus);
        return Result.success("t1");
    }
}
