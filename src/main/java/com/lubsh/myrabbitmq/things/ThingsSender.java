package com.lubsh.myrabbitmq.things;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThingsSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/thingsSend")
    public void send() {
        for (int i =0; i< 10; i++) {
            String msg = "things, 序号: " + i;
            System.out.println("Producer, " + msg);

            rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                String correlationId = message.getMessageProperties().getCorrelationIdString();
                System.out.println("消息:"+correlationId+"发送失败, 应答码："+replyCode+"原因："+replyText+"交换机:"+exchange+"路由键:"+routingKey);

            });
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if (!ack) {
                    System.out.println("thingsSender消息发送失败" + cause + correlationData.toString());
                } else {
                    System.out.println("thingsSender 消息发送成功 ");
                }
            });
            rabbitTemplate.convertAndSend("things", msg);
        }
    }
}
