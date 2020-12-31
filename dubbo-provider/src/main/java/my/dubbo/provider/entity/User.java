package my.dubbo.provider.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author _lizy
 * @version 1.0
 * @description 用户
 * @date 2020/10/17 20:53
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 9093812076747311903L;

    private Integer id;
    private String name;
    private Integer age;
    private Date createTime;





}
