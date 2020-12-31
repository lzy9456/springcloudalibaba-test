package com.example.springaop.redis;


import java.lang.annotation.*;


/**
 * my redis缓存注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface RedisAnno {

    /**
     * 缓存取值表达式，
     *  eg: #id 去方法参数id的值
     * @return
     */
    String value() default "";


    /**
     * 缓存取值key，
     *  eg: #id 去方法参数id的值
     * @return
     */
    String key() default "";
}
