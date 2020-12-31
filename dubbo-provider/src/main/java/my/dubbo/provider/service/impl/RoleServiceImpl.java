package my.dubbo.provider.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import my.dubbo.provider.dao.ExtRoleMapper;
import my.dubbo.provider.dao.auto.RoleMapper;
import my.dubbo.provider.entity.Role;
import my.dubbo.provider.entity.dto.RoleDto;
import my.dubbo.provider.service.IRoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author _lizy
 * @version 1.0
 * @description user service
 * @date 2020/10/17 20:50
 */
@Slf4j
@DubboService(version = "1.0.0")
public class RoleServiceImpl extends ServiceImpl<ExtRoleMapper, Role> implements IRoleService {


    @Override
    @SentinelResource(value = "getRole", fallback = "helloFallback", blockHandler = "blockHandler4GetRole")
    public Role getRole(Integer id) {
        Role role = baseMapper.selectById(id);
        int i= 1/0; // TODO TEST
        return role;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer update(RoleDto dto) {
        Role role = baseMapper.selectById(dto.getId());
        role.setName(dto.getName());
        int i= 1/0; // TODO TEST
        return baseMapper.updateById(role);
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public Role helloFallback(Integer id) {
        log.error("调用role异常");
        return null;
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public Role blockHandler4GetRole(Integer id, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        log.error("调用role异常 Oops, error occurred at {}",id);
        return null;
    }

}
