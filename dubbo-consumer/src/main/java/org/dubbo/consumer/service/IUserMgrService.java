package org.dubbo.consumer.service;

import com.example.entity.result.Result;
import my.dubbo.provider.entity.dto.UserDto;

/**
 * @author _lizy
 * @version 1.0
 * @description TODO
 * @date 2020/12/25 16:00
 */
public interface IUserMgrService {
    Result getUser(Integer id);
    Result updateUser(UserDto userDTO);
}
