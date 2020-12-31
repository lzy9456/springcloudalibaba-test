package my.dubbo.provider.mybatisplus;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import my.dubbo.provider.DubboProviderApp;
import my.dubbo.provider.entity.User;
import my.dubbo.provider.entity.dto.UserDto;
import my.dubbo.provider.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * JUnit test for mybatisplus.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={DubboProviderApp.class})// 指定启动类
public class MybatisPlusTest {

    @Resource
    private IUserService userService;

    @Test
    public void selectByIdTest(){
        System.out.println(JSON.toJSONString(userService.getUser(199)));
    }

    @Test
    public void selectPageTest(){
        UserDto userDTO = new UserDto();
        userDTO.setAge(20);
//        userDTO.setName("lizy");
//        userDTO.setNameLike("199l");
        userDTO.setPage(new Page().setCurrent(1).setSize(10));
        IPage<User> userPage = userService.getUserPage(userDTO);
        System.out.println(JSON.toJSONString(userPage));
    }


//    @Test
//    public void countByNameTest(){
//        System.out.println(userService.countByName("199l"));
//    }
//
//
//    @Test
//    public void findByNameTest(){
//        System.out.println(JSON.toJSONString(userService.findByName("199l")));
//    }



    
}
