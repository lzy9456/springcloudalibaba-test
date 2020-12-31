package my.dubbo.provider.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import my.dubbo.provider.BaseTest;
import my.dubbo.provider.DubboProviderApp;
import my.dubbo.provider.entity.User;
import my.dubbo.provider.entity.dto.RoleDto;
import my.dubbo.provider.entity.dto.UserDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * JUnit test for mybatisplus.
 */
public class RoleServiceTest extends BaseTest {

    @Resource
    private IRoleService roleService;

    @Test
    public void selectByIdTest(){
        System.out.println(JSON.toJSONString(roleService.getRole(1)));
    }

    @Test
    public void updateTest(){
        Integer updateRI = roleService.update(RoleDto.newRD().setId(1).setName(RandomStringUtils.randomAlphanumeric(6)));
        System.out.println(JSON.toJSONString(updateRI));
    }



    
}
