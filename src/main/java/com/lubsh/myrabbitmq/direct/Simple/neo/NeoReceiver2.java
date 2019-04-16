package com.lubsh.myrabbitmq.direct.Simple.neo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NeoReceiver2 {

    @RabbitListener(queues = "neo")
    public void process(String neo) {
        System.out.println("Receiver 2: " + neo);
    }

}
