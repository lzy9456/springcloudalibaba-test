package my.dubbo.sale.calc.impl;

import com.example.salecalc.context.SaleAnno;
import com.example.salecalc.context.SaleTypeEnum;
import com.example.utils.CalcUtil;
import my.dubbo.sale.calc.ISaleCalcExt;
import my.dubbo.sale.domain.CashBackDOExt;
import org.springframework.stereotype.Service;

/**
 * 满减
 */
@Service
@SaleAnno(value="cashBackExt", type = SaleTypeEnum.CASH_BACK)
public class CashBackExt implements ISaleCalcExt<String, CashBackDOExt> {

    @Override
    public String sale(CashBackDOExt param) {
        CashBackDOExt inParam = (CashBackDOExt) param;
        return ( inParam.valid() && inParam.satisfied() )
                ? CalcUtil.sub(inParam.getMoney(), inParam.getSubMoney())
                : inParam.getMoney();
    }

}
