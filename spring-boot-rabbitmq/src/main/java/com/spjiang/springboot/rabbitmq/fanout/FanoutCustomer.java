package com.spjiang.springboot.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Package: com.spjiang.springboot.fanout
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-17 01:45
 */
@Component
public class FanoutCustomer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 创建一个临时队列
                    exchange = @Exchange(value = "logs", type = "fanout") // 绑定交换机
            )
    })
    public void receive1(String message) {
        System.out.println("message1:" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 创建一个临时队列
                    exchange = @Exchange(value = "logs", type = "fanout") // 绑定交换机
            )
    })
    public void receive2(String message) {
        System.out.println("message2:" + message);
    }

//    @RabbitListener(queues="test")
//    public void receive3(String message) {
//        System.out.println("message2:" + message);
//    }
}
