package my.dubbo.sale.domain;

import com.example.salecalc.entity.BaseSaleDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * @author _lizy
 * @description 领域对象？
 * @date 2021/1/5 20:48
 */
@Data
@Accessors(chain = true)
@Component
public class DiscountDOExt extends BaseSaleDO {
    private String discount; //折扣率


    public static DiscountDOExt getInstance() {
        return new DiscountDOExt();
    }


}
