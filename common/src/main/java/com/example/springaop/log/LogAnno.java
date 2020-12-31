package com.example.springaop.log;


import java.lang.annotation.*;


/**
 * my log 日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface LogAnno {

    /**
     * 是否启用log
     */
    boolean value() default true;



}
