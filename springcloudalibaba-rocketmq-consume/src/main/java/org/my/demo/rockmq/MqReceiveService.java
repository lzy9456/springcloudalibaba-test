package org.my.demo.rockmq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author _lizy
 * @description TODO
 * @date 2021/1/2 14:54
 */
@Service
@EnableBinding({ MqReceiveService.MySink.class })
public class MqReceiveService {


    @StreamListener("input1")
    public void receiveInput1(String receiveMsg) {
        System.out.println("input1 receive: " + receiveMsg);
    }

    @StreamListener("input2")
    public void receiveInput2(String receiveMsg) {
        System.out.println("input2 receive: " + receiveMsg);
    }

    @StreamListener("input3")
    public void receiveInput3(@Payload Foo foo) {
        System.out.println("input3 receive: " + foo);
    }

    @StreamListener("input4")
    public void receiveTransactionalMsg(String transactionMsg) {
        System.out.println("input4 receive transaction msg: " + transactionMsg);
    }






    public interface MySink {

        @Input("input1")
        SubscribableChannel input1();

        @Input("input2")
        SubscribableChannel input2();

        @Input("input3")
        SubscribableChannel input3();

        @Input("input4")
        SubscribableChannel input4();

        @Input("input5")
        PollableMessageSource input5();

    }
}
