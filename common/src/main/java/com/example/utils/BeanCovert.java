package com.example.utils;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author _lizy
 * @version 1.0
 * @description
 * @date 2020/11/27 22:04
 */
@Log
public class BeanCovert<T> {

    /**
     * 目前仅支持普通public java bean，源对象属性绝对覆盖到目标对象。
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T transf(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {// 判断source是否为空,判断targetClass是否为空
            return null;
        }

        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return null;
    }


}