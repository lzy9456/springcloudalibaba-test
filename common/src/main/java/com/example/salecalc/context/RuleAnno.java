package com.example.salecalc.context;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 *
 * SaleAnno有注解到类或方法上有value、tyep属性，spring不能识别Autowire注入指定上下文
 * @author _lizy
 * @date 2020/12/31 17:49
 */
@Qualifier
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RuleAnno {

    String value() default "";  // value = discountService
    SaleTypeEnum type() default SaleTypeEnum.NON;       // type = SaleTypeEnum.DISCOUNT
}
