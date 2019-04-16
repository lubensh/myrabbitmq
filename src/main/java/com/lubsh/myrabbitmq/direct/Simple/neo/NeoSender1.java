package com.lubsh.myrabbitmq.direct.Simple.neo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NeoSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping(value = "/neoSend1")
    public void send(int i) {
        String context = "spirng boot neo queue"+" ****** "+i;
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("neo", context);
    }

}
