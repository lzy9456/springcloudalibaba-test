package com.example.springcontext;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 *
 * @author _lizy
 * @date 2020/12/31 17:49
 */
@Qualifier
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SaleAnno {

    String value() default "";  // value = discountService
    int type() default 0;       // type = SaleTypeEnum.DISCOUNT.code()
}
