package com.example.salecalc.context;


public enum RuleEnum {
    NON(0),
    DISCOUNT(1001), // 折扣
    CASH_BACK_SHARD(1002),  // 共享计算，流计算，按顺序传入折扣后金额判断是否满足满减
    CASH_BACK_SELF(1003),   // 独立计算，流计算，按原始金额判断是否满足满减
    COUPON(1004), // 优惠券
    ;

    public int code;

    RuleEnum(int code) {
        this.code = code;
    }


    // 根据code属性获取枚举类
    public static RuleEnum getByCode(int code) {
        for (RuleEnum c : RuleEnum.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return NON;
    }

    // 根据name属性获取枚举类
    public static RuleEnum getByName(String name) {
        RuleEnum saleTypeEnum = RuleEnum.valueOf(name);
        return saleTypeEnum == null ? NON : saleTypeEnum;
    }

}
