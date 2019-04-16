package com.lubsh.myrabbitmq.things.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
public class ThingsConfig {
    @Resource
    private RabbitTemplate rabbitTemplate;


    /**
    定义一个hello的队列
    Queue 可以有4个参数
    1.队列名
    2.durable       持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
    3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
    4.exclusive     表示该消息队列是否只在当前connection生效,默认是false*/

    @Bean
    public Queue thingsQueue(){
        return new Queue("things");
    }

    /****************************定制一些处理策略***************************/


    /**
     * 定制化amqp模版
     *
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     */
    /*@Bean
    public RabbitTemplate rabbitTemplate(){

        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        //消息返回, yml需要配置 publisher-returns: true

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationIdString();
            System.out.println("消息:"+correlationId+"发送失败, 应答码："+replyCode+"原因："+replyText+"交换机:"+exchange+"路由键:"+routingKey);

        });


        // 消息确认, yml需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("消息发送到exchange成功,id:"+correlationData.getId());
            } else {
                System.out.println("消息发送到exchange失败,原因:"+cause);
            }
        });
        return rabbitTemplate;
    }*/



}
