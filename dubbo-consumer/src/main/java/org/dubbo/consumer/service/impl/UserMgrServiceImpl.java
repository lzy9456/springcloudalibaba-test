package org.dubbo.consumer.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.entity.result.Result;
import io.seata.spring.annotation.GlobalTransactional;
import my.dubbo.provider.entity.Role;
import my.dubbo.provider.entity.User;
import my.dubbo.provider.entity.dto.RoleDto;
import my.dubbo.provider.entity.dto.UserDto;
import my.dubbo.provider.service.IRoleService;
import my.dubbo.provider.service.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dubbo.consumer.entity.UserVo;
import org.dubbo.consumer.service.IUserMgrService;
import com.example.utils.BeanCovert;
import org.springframework.stereotype.Service;

/**
 * @author _lizy
 * @version 1.0
 * @description TODO
 * @date 2020/12/25 16:01
 */
@Service
public class UserMgrServiceImpl implements IUserMgrService {

    @DubboReference(version = "1.0.0")
    private IUserService userService;
    @DubboReference(version = "1.0.0")
    private IRoleService roleService;



    @Override
    public Result getUser(Integer id) {
        User user = userService.getUser(1);
        Role role = roleService.getRole(1);


        String userStr = JSON.toJSONString(user);
        String roleStr = JSON.toJSONString(role);


        return Result.success(BeanCovert.transf(user, UserVo.class));
    }

    @Override
    @GlobalTransactional
    public Result updateUser(UserDto userDTO) {
        Integer updateUI = userService.update(userDTO);
        Integer updateRI = roleService.update(RoleDto.newRD().setId(1).setName(RandomStringUtils.randomAlphanumeric(5)));


        String userStr = JSON.toJSONString(updateUI);
        String roleStr = JSON.toJSONString(updateRI);


        return Result.success();
    }
}
