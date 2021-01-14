package my.dubbo.sale.rule.compose;

/**
 * @author _lizy
 * @description 多个打折组合方式
 * @date 2021/1/6 20:53
 */
public enum ComposeRuleEnum {
    NON(0),
    STREAM(1001),   // 流式计算，共享
    SELF(1002),     // 独立
    COUPON(1003),   // 优惠券
    ;

    public int code;

    ComposeRuleEnum(int code) {
        this.code = code;
    }


    // 根据code属性获取枚举类
    public static ComposeRuleEnum getByCode(int code) {
        for (ComposeRuleEnum c : ComposeRuleEnum.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return NON;
    }

    // 根据name属性获取枚举类
    public static ComposeRuleEnum getByName(String name) {
        ComposeRuleEnum saleTypeEnum = ComposeRuleEnum.valueOf(name);
        return saleTypeEnum == null ? NON : saleTypeEnum;
    }

}
