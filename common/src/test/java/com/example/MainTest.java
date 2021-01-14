package com.example;

import com.alibaba.fastjson.JSON;
import com.example.utils.BeanCovert;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Unit test for simple App.
 */
public class MainTest
{

    public static void main(String[] args) {
        String s = "";
        UserVo userVo = BeanCovert.transf(User.newU(), UserVo.class);
        System.out.println(JSON.toJSONString(userVo));





    }

    @Data
    @Accessors(chain = true)
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
    @Accessors(chain = true)
    public static class UserVo {
        public String id;
        public String name;
    }
}
