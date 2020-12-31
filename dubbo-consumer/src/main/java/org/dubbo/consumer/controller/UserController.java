package org.dubbo.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.example.entity.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import my.dubbo.provider.entity.dto.UserDto;
import org.dubbo.consumer.entity.UserVo;
import org.dubbo.consumer.service.IUserMgrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @Api - tags-菜单分组名，value名称
 * 参数验证valication -  @Valid开启验证 @NotBlank
 *
 *
 *
 * @author _lizy
 * @version 1.0
 * @description 测试类
 * @date 2020/10/17 21:26
 */
@Slf4j
@RestController
@Api(value = "用户controoller", tags = "AdminMgrSys v1.0 - 用户模块")
//@RequestMapping("/user")
@Validated
public class UserController {


    @Autowired
    private IUserMgrService userMgrService;


    /**
     * http get
     * API接口文档swagger -  @ApiOperation、@ApiImplicitParam
     * 参数验证valication -  @Valid开启验证 @NotBlank
     * 降级sentinel fallback - userMgrService.getUser - roleService.getRole()抛错降级执行fallback方法，跳过，不影响主流程
     *
     * @param id
     * @return Result 统一响应返回前端
     */
    @ResponseBody
    @RequestMapping(value = "/sayhello/{id}", method = GET)
    @ApiOperation(value = "say hello", notes = "say hello")
    @ApiImplicitParam(name = "id", value = "用户名id", required = true, dataType = "String", paramType = "path")
    public Result<UserVo> sayHello(@Valid @NotBlank(message = "id不能为空") @PathVariable("id") String id) {
        Result userR = userMgrService.getUser(Optional.ofNullable(id).map(i -> Integer.valueOf(i)).orElse(null));


        log.info(JSON.toJSONString(userR));
        return Result.success(userR.getData(), UserVo.class);
    }


    /**
     * http post
     * API接口文档swagger -  @ApiOperation、@ApiImplicitParam
     * 参数验证valication -  @Valid
     * 降级sentinel fallback - userMgrService.getUser - roleService.getRole()抛错降级执行fallback方法，跳过，不影响主流程
     *
     * @param userVo
     * @return Result 统一响应返回前端
     */
    @ResponseBody
    @RequestMapping(value = "/getUser", method = POST)
    @ApiOperation(value = "get user info", notes = "获取用户信息")
    public Result getUser(@Valid @RequestBody UserVo userVo) {
        Result userR = userMgrService.getUser(1);
        String userStr = JSON.toJSONString(userR.getData());

        log.info(userStr);
        System.out.println("user:" + userStr);
        return Result.success(userR.getData(), UserVo.class);
    }


    /**
     * http post
     * API接口文档swagger -  @ApiOperation、@ApiImplicitParam
     * 参数验证valication -  @Valid @NotBlank @Digits
     * 分布式事务seata -  userMgrService.updateUser ->
     *
     * @param id
     * @param name
     * @return Result 统一响应返回前端
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = GET)
    @ApiOperation(value = "update user", notes = "update user, http get test")
    @ApiImplicitParams({
            @ApiImplicitParam(name= "id", value= "用户名id", required=true),
            @ApiImplicitParam(name= "name", value= "姓名", required=true)
    })
    public Result<UserVo> update(@Valid  @NotBlank(message = "id不能为空") @Digits(integer = 10, fraction = 0, message = "id必须为数字") String id,
                                 @Valid  @NotBlank(message = "name不能为空") String name) {
        Result userR = userMgrService.updateUser(UserDto.newUD().setId(Integer.valueOf(id)).setName(name));


        log.info(JSON.toJSONString(userR));
        return Result.success(userR.getData(), UserVo.class);
    }

    @ResponseBody
    @RequestMapping(value = "/say", method = GET)
    public String say() {
//        System.out.println(userService.getUser(1));
        log.info("ok");
        return "ok";
    }

}
