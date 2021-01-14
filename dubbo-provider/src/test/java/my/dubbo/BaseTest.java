package my.dubbo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * JUnit test for mybatisplus.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={DubboProviderApp.class})// 指定启动类
public class BaseTest {
}
