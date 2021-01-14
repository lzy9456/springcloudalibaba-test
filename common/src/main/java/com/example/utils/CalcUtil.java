package com.example.utils;

import java.math.BigDecimal;

/**
 * @description 精度计算工具类
 *      少用double，尽量全程使用string or 转为long
 * @date 2021/1/5 20:13
 */
public class CalcUtil {

    public static final String ZERO = "0";

    /**
     * 加法
     *
     * @param var1
     * @param var2
     * @return
     */
    public static String add(String var1, String var2) {
        BigDecimal b1 = new BigDecimal(var1);
        BigDecimal b2 = new BigDecimal(var2);
        return b1.add(b2).toString();
    }

    /**
     * 减法
     *
     * @param var1
     * @param var2
     * @return
     */
    public static String sub(String var1, String var2) {
        BigDecimal b1 = new BigDecimal(var1);
        BigDecimal b2 = new BigDecimal(var2);
        return b1.subtract(b2).toString();
    }

    /**
     * 乘法
     *
     * @param var1
     * @param var2
     * @return
     */
    public static String mul(String var1, String var2) {
        BigDecimal b1 = new BigDecimal(var1);
        BigDecimal b2 = new BigDecimal(var2);
        return b1.multiply(b2).toString();
    }

    /**
     * 除法
     *
     * @param var1    被除数
     * @param var2    除数
     * @param scale 精度，到小数点后几位
     * @return
     */
    public static String div(String var1, String var2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(var1);
        BigDecimal b2 = new BigDecimal(var2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 四舍五入
     *
     * @param v
     * @param scale 精确位数
     * @return
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 加法计算并保留小数点
     */
    public static String addRound(String var1, String var2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0");
        }

        return round(add(var1, var2), scale);
    }

    /**
     * 减法计算并保留小数点
     */
    public static String subRound(String var1, String var2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0");
        }
        return round(sub(var1, var2), scale);
    }

    /**
     * 乘法计算并保留小数点
     *
     */
    public static String mulRound(String var1, String var2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0");
        }
        return round(mul(var1, var2), scale);
    }


    /**
     * 比较var1，var2大小
     * @return int(1大于, 0等于,  -1小于)
     */
    public static int compareTo(String var1, String var2) {
        if (var1 == null  || var2 == null) {
            throw new NullPointerException("不能为空");
        }
        BigDecimal b1 = new BigDecimal(var1);
        BigDecimal b2 = new BigDecimal(var2);
        return b1.compareTo(b2);
    }


    /**
     * 小于0
     * @return int(1大于, 0等于,  -1小于)
     */
    public static boolean lessZero(String var1) {
        if (var1 == null ) {
            throw new NullPointerException("不能为空");
        }
        BigDecimal b1 = new BigDecimal(var1);
        BigDecimal b2 = new BigDecimal(ZERO);
        return b1.compareTo(b2)<0;
    }
}
