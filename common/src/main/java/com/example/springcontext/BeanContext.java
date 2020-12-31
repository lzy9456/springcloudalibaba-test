package com.example.springcontext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanContext {
    static Map context = new ConcurrentHashMap();

    @Autowired
    private ApplicationContext applicationContext;





    @Autowired
    @SaleAnno
    public void setService(ISale saleService){
        SaleAnno saleAnno = saleService.getClass().getAnnotation(SaleAnno.class);
        context.put(saleAnno.type(), saleAnno.value());
    }
}
