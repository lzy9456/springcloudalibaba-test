package com.example.utils;

import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;

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


    /**
     * 目前仅支持普通public java bean，源对象属性绝对覆盖到目标对象。
     * @param source
     * @param target
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T transf(Object source, T target) {
        if (source == null || target == null) {// 是否为空
            return null;
        }

        try {
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return null;
    }


}