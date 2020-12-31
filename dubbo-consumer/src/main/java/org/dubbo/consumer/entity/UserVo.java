package org.dubbo.consumer.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author _lizy
 * @version 1.0
 * @description TODO
 * @date 2020/12/19 14:58
 */
@Data
@ApiModel("用户信息vo")
public class UserVo {

    @NotNull
    @ApiModelProperty(value = "用户id",dataType="String",name="id",required = true)
    private Integer id;

    @ApiModelProperty(value = "用户名", name="name",example="lizy",required = true)
    private String name;

    @ApiModelProperty(value = "用户地址", name="addr",required = false,example="成都")
    private String addr;
}
