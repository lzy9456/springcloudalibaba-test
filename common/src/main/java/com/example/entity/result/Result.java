package com.example.entity.result;

import com.example.utils.BeanCovert;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author _lizy
 * @version 1.0
 * @description 统一返回结果封装
 * @date 2020/12/19 15:06
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    public T data;
    public String code;
    public String msg;



    public static <T> Result<T> success() {
        return result(CodeEnum.SUCCESS, null);
    }

    public static <T> Result<T> fail() {
        return result(CodeEnum.FAIL, null);
    }


    public static <T> Result<T> success(T data) {
        return result(CodeEnum.SUCCESS, data);
    }

    public static <T,R> Result<R> success(T data, Class<R> clazz) {
        return result(CodeEnum.SUCCESS, BeanCovert.transf(data, clazz));
    }

    public static <T> Result<T> fail(T data) {
        return result(CodeEnum.FAIL, data);
    }

    public static Result fail(String msg) {
        return result(CodeEnum.FAIL.code, msg, null);
    }




    public static <T> Result<T> result(CodeEnum code, T data) {
        return result(code.code, code.msg, data);
    }

    public static <T> Result<T> result(String code, String msg, T data) {
        Result result = newR(); // 测试new生成比new静态类更快或相差不大
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static Result newR() {
        return new Result();
    }





}
