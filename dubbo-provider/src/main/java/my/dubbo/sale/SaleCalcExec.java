package my.dubbo.sale;

import com.example.salecalc.context.SaleContext;
import com.example.salecalc.context.SaleTypeEnum;
import com.example.salecalc.entity.BaseSaleDO;
import lombok.extern.slf4j.Slf4j;
import my.dubbo.sale.domain.SaleDTO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @author _lizy
 * @description TODO
 * @date 2021/1/6 0:07
 */
@Slf4j
@Service
public class SaleCalcExec{


    @Autowired
    private SaleContext saleContext;


    public String sale(SaleDTO saleDTO){
        return calcExec(saleDTO);
    }

    private String calcExec(SaleDTO saleDTO) {
        String resultMoney = saleDTO.getMoney();
        Map<Integer, BaseSaleDO> saleParamMap = saleDTO.getSaleParamMap(); // 可同享的多个折扣列表<折扣类型code,参数>
        for (Integer code : saleParamMap.keySet()) {

            String finalResultMoney = resultMoney;

            // 获取对应折扣计算
            // resultMoney更新为折扣计算后结果
            resultMoney = Optional.ofNullable(code)
                    .map( temp -> saleParamMap.get(code))   // 为空跳过
                    .map( inputParam -> NumberUtils.toInt(code+""))
                    .map( codeI -> SaleTypeEnum.getByCode(codeI))
                    .map( saleTypeEnum -> saleContext.getService(saleTypeEnum))
                    .map( service -> (String)service.sale(saleParamMap.get(code).setMoney(finalResultMoney)))   // 更新计算money
                    .orElse(resultMoney);
            log.info("code: {}, tempResultMoney: {}", code, resultMoney);
        }
        return resultMoney;
    }


}
