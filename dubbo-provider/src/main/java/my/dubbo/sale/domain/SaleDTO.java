package my.dubbo.sale.domain;

import com.example.salecalc.entity.BaseSaleDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author _lizy
 * @description
 * @date 2021/1/6 0:14
 */
@Data
@Accessors(chain = true)
public class SaleDTO {
    private String money;
//    private List<SaleTypeEnum> saleTypes;
    private Map<Integer, BaseSaleDO> saleParamMap = new HashMap<>(); // 可同享的多个折扣列表<折扣类型code,参数>

}
