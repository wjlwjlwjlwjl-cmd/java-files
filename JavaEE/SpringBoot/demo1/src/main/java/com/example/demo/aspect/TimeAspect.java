package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
//切面类
public class TimeAspect {
    @Pointcut("execution(* com.example.demo.controller.*.*(..))") //切点（切点表达式）
    private void pt(){}

    //切面
    @Around("pt()")
    public Object timeRecord(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info(pjp.getSignature().toString() + (end - start) + "ms spend");
        return proceed;
    }
}