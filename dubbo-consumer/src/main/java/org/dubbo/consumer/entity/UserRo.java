package org.dubbo.consumer.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author _lizy
 * @version 1.0
 * @description TODO
 * @date 2020/12/19 14:59
 */
@Data
@Accessors(chain = true)
public class UserRo {
    private Integer id;
    private String name;
    private String addr;
}
