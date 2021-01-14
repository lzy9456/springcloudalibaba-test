package com.example.springaop.log;


import com.alibaba.fastjson.JSON;
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
        log.info("************** {} begin, {}", mName, appendObjLog(point.getArgs()));


        Object obj = point.proceed();

        log.info("************** {} end, {}", mName, appendObjLog(obj));

        return obj;
    }


    private String appendObjLog(Object obj) {
        try {
            return new StringBuilder(obj.getClass().getSimpleName()).append(" ").append(toJsonStr(obj)).toString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("转换json异常",e);
        }
        return "null";
    }

    private String toJsonStr(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("转换json异常",e);
        }
        return "null";
    }
}
