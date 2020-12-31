package com.example.springaop.log;


import com.example.springaop.redis.RedisAnno;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * my log aspect 处理
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("@annotation(logAnno)")
    public Object doAround(ProceedingJoinPoint point, LogAnno logAnno) throws Throwable {
        String mName = point.getSignature().getName();
        log.info("************** {} log begin, do something", mName);


        Object obj = point.proceed();

        log.info("**************  end, do something");

        return obj;
    }
}
