package my.dubbo.provider;

import com.alibaba.fastjson.JSON;
import my.dubbo.provider.entity.dto.RoleDto;
import my.dubbo.provider.service.IRoleService;
import org.apache.commons.lang3.RandomStringUtils;
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
public class BaseTest {
}
