package my.dubbo.genricclass;

import lombok.extern.slf4j.Slf4j;
import my.dubbo.provider.entity.Role;
import my.dubbo.provider.entity.dto.RoleDto;

/**
 * 满减
 */
@Slf4j
public class RoleDaoImpl extends BaseDao<RoleDto, Role> implements IDao<RoleDto, Role> {


    public RoleDto getById(int id){
        return toDto(new Role().setId(id).setName("lziy").setRoleType(23));
    }




}
