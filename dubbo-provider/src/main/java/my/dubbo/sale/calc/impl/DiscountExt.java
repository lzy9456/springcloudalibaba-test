package my.dubbo.sale.calc.impl;

import com.example.salecalc.context.SaleAnno;
import com.example.salecalc.context.SaleTypeEnum;
import com.example.utils.CalcUtil;
import my.dubbo.sale.calc.ISaleCalcExt;
import my.dubbo.sale.domain.DiscountDOExt;
import org.springframework.stereotype.Service;



/**
 * 打折
 */
@Service
@SaleAnno(value="discountExt", type = SaleTypeEnum.DISCOUNT)
public class DiscountExt implements ISaleCalcExt<String, DiscountDOExt> {


    /**
     * 前置条件：验证参数 and 规则
     * @param param
     * @return
     */
    @Override
    public String sale(DiscountDOExt param) {
        DiscountDOExt dp = (DiscountDOExt) param;
        return CalcUtil.mul(dp.getMoney(), dp.getDiscount());
    }



}
