package com.lubsh.myrabbitmq.direct.Simple.object;

import com.lubsh.myrabbitmq.direct.Simple.object.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ObjectReceiver {


    @RabbitListener(queues = "object")
    public void process(User user) {
        System.out.println("Receiver object : " + user);
    }
}
