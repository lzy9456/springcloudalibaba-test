package my.dubbo.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import my.dubbo.provider.entity.Permission;
import my.dubbo.provider.entity.Role;
import my.dubbo.provider.entity.dto.RoleDto;
import my.dubbo.provider.entity.dto.UserDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lizy
 * @since 2020-10-18
 */
public interface IRoleService extends IService<Role> {

    Role getRole(Integer id);
    Integer update(RoleDto roleDto);
}
