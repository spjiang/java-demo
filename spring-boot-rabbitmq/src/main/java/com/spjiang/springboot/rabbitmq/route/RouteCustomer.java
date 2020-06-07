package com.spjiang.springboot.rabbitmq.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Package: main.spjiang.springboot.route
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-17 01:57
 */
@Component
public class RouteCustomer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 创建一个临时队列
                    exchange = @Exchange(value = "directs", type = "direct"), // 绑定交换机
                    key = {"info", "error", "warning"}

            )
    })
    public void receive1(String message) {
        System.out.println("message1:" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 创建一个临时队列
                    exchange = @Exchange(value = "directs", type = "direct"), // 绑定交换机
                    key = {"warning"}

            )
    })
    public void receive2(String message) {
        System.out.println("message2:" + message);
    }
}
