package com.example.salecalc.entity;

/**
 * @author _lizy
 * @description TODO
 * @date 2021/1/5 20:45
 */
public class BaseSaleDO {
    public String money;    // 计算金额
    public String baseMoney; //原始金额


    public String getMoney() {
        return money;
    }
    public BaseSaleDO setMoney(String money) {
        this.money = money;
        return this;
    }
    public String getBaseMoney() {
        return baseMoney;
    }
    public BaseSaleDO setBaseMoney(String baseMoney) {
        this.baseMoney = baseMoney;
        return this;
    }
}
