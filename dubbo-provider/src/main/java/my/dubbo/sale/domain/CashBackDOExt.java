package my.dubbo.sale.domain;

import com.example.salecalc.entity.BaseSaleDO;
import com.example.utils.CalcUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

/**
 * @author _lizy
 * @description 满减
 * @date 2021/1/5 20:48
 */
@Data
@Accessors(chain = true)
@Component
public class CashBackDOExt extends BaseSaleDO {
    private String reachStandardMoney;      //满减达标金额
    private String subMoney;                //优惠金额
    private boolean validBase=false;        //是否按原始金额验证，是按原始金额baseMoney验证,否验证计算后money


    public static CashBackDOExt getInstance() {
        return new CashBackDOExt();
    }
    /**
     * 是否满足满减优惠
     *      satisfied 强制满足
     * @return boolean
     */
    public boolean satisfied(){
        return validBase ? CalcUtil.compareTo(baseMoney, reachStandardMoney) >= 0 : CalcUtil.compareTo(money, reachStandardMoney) >= 0;
    }

    /**
     * 验证
     *     是否数字（包含不为空）
     * @return boolean
     */
    public boolean valid(){
        return NumberUtils.isCreatable(money)
                && NumberUtils.isCreatable(baseMoney)
                && NumberUtils.isCreatable(reachStandardMoney)
                && NumberUtils.isCreatable(subMoney);
    }


}
