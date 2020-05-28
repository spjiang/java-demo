package main.spjiang.springboot.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Package: main.spjiang.springboot.topic
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-17 02:08
 */
@Component
public class TopicCustomer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 创建一个临时队列
                    exchange = @Exchange(value = "topics", type = "topic"), // 绑定交换机
                    key = {"user.save", "user.*"}
            )
    })
    public void receive1(String message) {
        System.out.println("message1:" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 创建一个临时队列
                    exchange = @Exchange(value = "topics", type = "topic"), // 绑定交换机
                    key = {"order.#", "user.*", "produce.#"}
            )
    })
    public void receive2(String message) {
        System.out.println("message2s:" + message);
    }
}
