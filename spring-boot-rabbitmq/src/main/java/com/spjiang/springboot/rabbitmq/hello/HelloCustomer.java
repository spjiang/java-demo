package com.spjiang.springboot.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Package: com.spjiang.springboot.hello
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-17 00:49
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello", durable = "true"))
public class HelloCustomer {
    @RabbitHandler
    public void receive1(String message) {
        System.out.println("message:" + message);
    }
}
