package com.lubsh.myrabbitmq.direct.Simple.hello;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloReceiver {

    @RabbitListener(queues = "hello")
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
}
