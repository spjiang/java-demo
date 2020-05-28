package com.spjiang.springboot.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Package: com.spjiang.springboot.work
 *
 * @description: 工作类型模式，工作队列不能重复消费
 *
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-17 01:02
 */
@Component
public class WorkCustomer {
    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive1(String message) {
        System.out.println("message1:" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive2(String message) {
        System.out.println("message2:" + message);
    }

}
