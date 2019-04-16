package com.lubsh.myrabbitmq.direct.Simple.object;

import com.lubsh.myrabbitmq.direct.Simple.object.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    @GetMapping(value = "/objectSend")
    public void send(User user) {
        System.out.println("Sender object: " + user.toString());
        this.rabbitTemplate.convertAndSend("object", user);
    }

}
