package com.example.springcontext;

public enum SaleTypeEnum {
    DISCOUNT(1001), // 折扣

    ;
    private Integer code;

    SaleTypeEnum(Integer code) {
        this.code = code;
    }
}
