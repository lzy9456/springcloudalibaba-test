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

        //TODO 缓存中取，有返回return obj

        Object obj = point.proceed();

        //TODO 缓存中没有，查询后更新到缓存，还可以异步or延迟解决数据一致性

        log.info("************** redis end");

        return obj;
    }
}
