package my.dubbo.provider.service;

import com.alibaba.fastjson.JSON;
import my.dubbo.provider.BaseTest;
import my.dubbo.provider.entity.dto.RoleDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import javax.annotation.Resource;


/**
 * JUnit test for mybatisplus.
 */
public class UserServiceTest extends BaseTest {

    @Resource
    private IUserService userService;

    @Test
    public void selectByIdTest(){

        System.out.println(JSON.toJSONString(userService.getUser(1)));
    }

    @Test
    public void updateTest(){
//        Integer updateRI = userService.update();
//        System.out.println(JSON.toJSONString(updateRI));
    }



    
}
