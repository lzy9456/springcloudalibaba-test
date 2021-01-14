package org.my.demo.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.my.demo.rocketmq.Constants;
import org.my.demo.rocketmq.Foo;
import org.my.demo.rocketmq.MqSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author _lizy
 * @description mq rest test
 * @date 2021/1/2 15:12
 */
@RestController
public class MqTestController {

    @Autowired
    private MqSenderService mqSenderService;

    @ResponseBody
    @RequestMapping(value = "/send1", method = GET)
    public String sendOutput1() throws Exception {
        mqSenderService.send(UUID.randomUUID().toString());
        return "ok "+System.currentTimeMillis();
    }



    @ResponseBody
    @RequestMapping(value = "/send2", method = GET)
    public String sendOp1Tags() throws Exception {
        mqSenderService.sendWithTags(UUID.randomUUID().toString(), Constants.MQ_TAG_UNREG_USER);
        return "tag ok "+System.currentTimeMillis();
    }


    @ResponseBody
    @RequestMapping(value = "/send3", method = GET)
    public String send3() throws Exception {
        mqSenderService.sendObject(Foo.newF().setId(1).setBar("test"), Constants.MQ_TAG_OBJ);
        return "obj ok "+System.currentTimeMillis();
    }



    @ResponseBody
    @RequestMapping(value = "/send4", method = GET)
    public String send4() throws Exception {
        mqSenderService.sendTransactionalMsg(UUID.randomUUID().toString(), Integer.valueOf(RandomStringUtils.randomNumeric(8)));
        return "tx ok "+System.currentTimeMillis();
    }


}
