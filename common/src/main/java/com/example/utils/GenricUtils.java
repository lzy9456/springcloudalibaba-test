package com.example.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author _lizy
 * @description TODO
 * @date 2021/1/12 20:00
 */
public class GenricUtils {
    /**
     * 获得泛型类型1
     *
     * @param clz
     * @return
     */
    public static <T> Class<T> getGenricClassType(Class clz) {
        return getGenricClassType(clz,0);
    }

    /**
     * 获得泛型类型1
     *
     * @param clz
     * @return
     */
    public static <T> Class<T> getGenricClassIdx1(Class clz) {
        return getGenricClassType(clz,0);
    }

    /**
     * 获得泛型类型2
     *
     * @param clz
     * @return
     */
    public static <T> Class<T> getGenricClassIdx2(Class clz) {
        return getGenricClassType(clz,1);
    }


    /**
     * 获得泛型类型
     *
     * @param clz
     * @return
     */
    public static <T> Class<T> getGenricClassType(Class clz, int index) {
        Type type = clz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Type[] types = pt.getActualTypeArguments();
            if (types.length > (index+1) && types[index] instanceof Class) {
                return (Class) types[index];
            }
        }
        return (Class) Object.class;
    }

}
