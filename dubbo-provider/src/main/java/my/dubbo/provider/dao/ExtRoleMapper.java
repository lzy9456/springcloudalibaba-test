package my.dubbo.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.dubbo.provider.dao.auto.RoleMapper;
import my.dubbo.provider.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lizy
 * @since 2020-10-18
 */
public interface ExtRoleMapper extends RoleMapper {
    @Select("select * from user where roleType like CONCAT('%',#{roleType},'%')")
    Role getByRoleType(@Param("roleType") String roleType);
}
