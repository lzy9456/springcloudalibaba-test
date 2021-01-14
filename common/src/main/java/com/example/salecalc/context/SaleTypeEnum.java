package com.example.salecalc.context;


public enum SaleTypeEnum {
    NON(0),
    DISCOUNT(1001), // 折扣
    CASH_BACK(1002), // 满减
    COUPON(1003), // 优惠券
    ;

    public int code;

    SaleTypeEnum(int code) {
        this.code = code;
    }


    // 根据code属性获取枚举类
    public static SaleTypeEnum getByCode(int code) {
        for (SaleTypeEnum c : SaleTypeEnum.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return NON;
    }

    // 根据name属性获取枚举类
    public static SaleTypeEnum getByName(String name) {
        SaleTypeEnum saleTypeEnum = SaleTypeEnum.valueOf(name);
        return saleTypeEnum == null ? NON : saleTypeEnum;
    }

}
