package my.dubbo.sale;


import com.example.salecalc.context.SaleContext;
import com.example.salecalc.context.SaleTypeEnum;
import com.example.salecalc.entity.BaseSaleDO;
import com.example.salecalc.service.ISaleCalc;
import lombok.extern.slf4j.Slf4j;
import my.dubbo.BaseTest;
import my.dubbo.sale.domain.CashBackDOExt;
import my.dubbo.sale.domain.DiscountDOExt;
import my.dubbo.sale.domain.SaleDTO;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author _lizy
 * @description TODO
 * @date 2021/1/6 0:24
 */
@Slf4j
public class SaleContextTest extends BaseTest {

    @Autowired
    private SaleContext saleContext;


    @Test
    public void getServiceTest(){
        ISaleCalc<String, BaseSaleDO> service = saleContext.getService(SaleTypeEnum.DISCOUNT);
        DiscountDOExt inputParam = DiscountDOExt.getInstance();
        inputParam.setMoney("100.23");
        inputParam.setDiscount("0.8");
        String resultMoney = service.sale(inputParam);
        System.out.println(resultMoney);


        service = saleContext.getService(SaleTypeEnum.CASH_BACK);
        CashBackDOExt cashBackInParam = CashBackDOExt.getInstance();
        cashBackInParam.setMoney("100.23");
        cashBackInParam.setSubMoney("20");
        resultMoney = service.sale(cashBackInParam);
        System.out.println(resultMoney);
    }


    @Test
    public void saleTest(){
        //模拟controller层封装DTO
        SaleDTO saleDTO = getSaleDTO();

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

        log.info("resultMoney: {}", resultMoney);

    }


    /**
     * 模拟controller层封装DTO
     * @return
     */
    private SaleDTO getSaleDTO() {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setMoney("100.23");

        Map<Integer, BaseSaleDO> saleParamMap = new HashMap<>();

        saleParamMap.put(1001, null);           // 测试，为空跳过

        DiscountDOExt discountInParam = DiscountDOExt.getInstance();
        discountInParam.setMoney("100.23");
        discountInParam.setDiscount("0.6");
        saleParamMap.put(SaleTypeEnum.DISCOUNT.code, discountInParam);

        saleParamMap.put(null, discountInParam); // 测试，为空跳过
        saleParamMap.put(0, discountInParam);   // 测试，为空跳过
//        saleParamMap.put(SaleTypeEnum.DISCOUNT.code, null);   // 同id，测试覆盖，为空跳过


        CashBackDOExt cashBackInParam = CashBackDOExt.getInstance();
        cashBackInParam.setMoney("100.23");
        cashBackInParam.setBaseMoney("100.23");
        cashBackInParam.setReachStandardMoney("100");
        cashBackInParam.setSubMoney("20");
        cashBackInParam.setValidBase(true);       // 以原始金额验证满减
//        cashBackInParam.setValidBase(false);    // 不以原始金额验证满减
        saleParamMap.put(SaleTypeEnum.CASH_BACK.code, cashBackInParam);
        saleDTO.setSaleParamMap(saleParamMap);

        return saleDTO;
    }
}
