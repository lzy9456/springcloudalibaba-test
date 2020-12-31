package my.dubbo.provider.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author _lizy
 * @version 1.0
 * @description TODO
 * @date 2020/12/25 17:00
 */
@Slf4j
public class BlockHandler {

    public static void exceptionHandler(Integer id, BlockException ex){
        log.error("调用异常",ex);
    }
}
