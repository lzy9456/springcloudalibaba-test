package com.example;

import com.alibaba.fastjson.JSON;
import com.example.utils.BeanCovert;
import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class MainTest
{

    public static void main(String[] args) {
        UserVo userVo = BeanCovert.transf(User.newU(), UserVo.class);
        System.out.println(JSON.toJSONString(userVo));
    }

    @Data
    public static class User {
        public String id;
        public String name;


        public static User newU(){
            User user = new User();
            user.setId("sdf");
            user.setName("li si");
            return user;
        }
    }

    @Data
    public static class UserVo {
        public String id;
        public String name;
    }
}
