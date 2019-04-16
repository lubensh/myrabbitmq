package com.lubsh.myrabbitmq.things;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ThingsReceiver {

    @RabbitListener(queues = "things")
    public void process(Message message, Channel channel) throws IOException {
        //消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //ack返回false，并重新回到队列，api里面解释得很清楚
        //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        //拒绝消息
        //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("Receiver, " + message.getBody());
    }
}
