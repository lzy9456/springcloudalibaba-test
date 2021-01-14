package com.example.salecalc.context;

import com.example.salecalc.service.ISaleCalc;
import com.example.salecalc.service.impl.DefaultSaleCalcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class SaleContext {
    Map<Integer, String> context = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;


    @Autowired(required=false)
    public void setSales(List<ISaleCalc> sales) {
        for (Iterator<ISaleCalc> iterator = sales.iterator(); iterator.hasNext(); ) {
            ISaleCalc sale = iterator.next();
            SaleAnno saleAnno = sale.getClass().getAnnotation(SaleAnno.class);

            Optional.ofNullable(saleAnno)
                    .map(sa -> saleAnno.type().code)
                    .map(sa -> saleAnno.value())
                    .map(sa -> context.put(saleAnno.type().code, saleAnno.value()));
        }
    }


    public ISaleCalc getService(SaleTypeEnum saleTypeEnum) {
        return Optional.ofNullable(saleTypeEnum)
                .map(ac -> context.get(saleTypeEnum.code))
                .map(serviceName -> applicationContext.getBean(serviceName))
                .map(sale -> (ISaleCalc) sale)
                .orElse((ISaleCalc) applicationContext.getBean(DefaultSaleCalcServiceImpl.class));
    }
}
