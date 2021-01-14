package com.example.salecalc.service.impl;

import com.example.salecalc.context.SaleAnno;
import com.example.salecalc.context.SaleTypeEnum;
import com.example.salecalc.entity.BaseSaleDO;
import com.example.salecalc.service.ISaleCalc;
import org.springframework.stereotype.Service;

@Service("defaultSaleService")
@SaleAnno(value="defaultSaleService", type = SaleTypeEnum.NON)
public class DefaultSaleCalcServiceImpl implements ISaleCalc<String, BaseSaleDO> {


    /**
     * @param baseSaleDO
     * @return
     */
    @Override
    public String sale(BaseSaleDO baseSaleDO) {
        return baseSaleDO.money;
    }

}
