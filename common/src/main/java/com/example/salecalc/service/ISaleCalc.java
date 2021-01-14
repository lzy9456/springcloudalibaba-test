package com.example.salecalc.service;

public interface ISaleCalc<R,T> {

    /**
     * 打折计算
     * @param param
     * @return String 打折后金额
     */
    public R sale(T param);
}
