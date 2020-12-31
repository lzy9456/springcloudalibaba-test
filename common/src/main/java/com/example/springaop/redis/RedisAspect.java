package com.example.springaop.redis;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * my redis aspect 处理
 */
@Aspect
@Component
@Slf4j
public class RedisAspect {

    @Around("@annotation(redisAnno)")
    public Object doAround(ProceedingJoinPoint point, RedisAnno redisAnno) throws Throwable {
        log.info("************** redis begin");


        Object obj = point.proceed();

        log.info("************** redis end");

        return obj;
    }
}
