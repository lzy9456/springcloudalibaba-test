package org.dubbo.consumer.controller;

import com.alibaba.fastjson.JSON;
import my.dubbo.provider.entity.User;
import my.dubbo.provider.service.IUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author _lizy
 * @version 1.0
 * @description TODO
 * @date 2020/10/17 21:26
 */
@RestController
public class WebSockController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @DubboReference(version = "1.0.0")
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/ws/{id}", method = GET)
    public String sayHello(@PathVariable("id") String id) {
        User user = userService.getUser(1);
        String userStr = JSON.toJSONString(user);

        logger.info(userStr);
        System.out.println("user:" + user.getId());
        return userStr;
    }

    @ResponseBody
    @RequestMapping(value = "/ws/say", method = GET)
    public String say() {
//        System.out.println(userService.getUser(1));
        return "ok";
    }
}
