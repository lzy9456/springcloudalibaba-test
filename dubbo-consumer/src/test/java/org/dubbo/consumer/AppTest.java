package org.dubbo.consumer;

import com.example.entity.result.Result;
import com.google.common.collect.Sets;
import my.dubbo.provider.entity.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    public static final int LOOP_COUNT = 2000000;

    public static void main(String[] args) {
//        setLambdaForeachTest();

        long time=0;
        for (int i = 0; i < 10; i++) {
            if(i>2){
                time+=genOneMillinsTimeTest();
            }else{
                genOneMillinsTimeTest();
            }
        }
        System.out.println("********平均"+LOOP_COUNT+":"+ time/7);


    }

    private static long genOneMillinsTimeTest() {
        long s_time = System.currentTimeMillis();
        for (int i = 0; i < LOOP_COUNT; i++) {
            genResult();
        }
        long result = System.currentTimeMillis() - s_time;
        System.out.println("运行"+LOOP_COUNT+":"+ result);
        return result;
    }

    private static Result genResult() {
        return Result.success(getData());
    }






    private static User getData() {
//        User user = new User();
//        user.setId(getInteger(3)+1);
//        user.setAge(getInteger(2) +1);
//        user.setName(RandomStringUtils.randomAlphanumeric(8));
//        user.setCreateTime(new Date());
//        return user;|
        return new User();
    }

    private static Integer getInteger(Integer bit) {
        return Integer.valueOf(RandomStringUtils.randomNumeric(bit));
    }

    /**
     * @Description
     *      set遍历
     *      foreach中lambda表达式使用return在这相当于循环中的continue，返回开始下一个
     * @Return void
     * @Author _lizy
     * @Date 2020/11/27 16:53
     */
    private static void setLambdaForeachTest() {
        final AtomicInteger ai = new AtomicInteger(0);
        Set<String> sessionIds = Sets.newHashSet("1001",null,"1311","5321","7224");
        sessionIds.forEach(sessionId -> {
            if(StringUtils.isBlank(sessionId)){ return; }   // return在这相当于循环中的continue，返回开始下一个
            ai.incrementAndGet();
        });
        System.out.println(ai.get());
    }



}
