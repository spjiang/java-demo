package com.spjiang.springboot.rabbitmq;

import com.spjiang.springboot.rabbitmq.SpringBootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = SpringBootRabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class RabbitmqTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    // topic 路由模式,动态路由，订阅模式
    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("topics", "user.save", "user.save 路由消息");
    }

    // route 路由模式
    @Test
    public void testRoute() {
        rabbitTemplate.convertAndSend("directs", "warning", "发送info的key的路由信息");
    }

    // fanout 广播
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs", "", "Fanout的模型发送的消息");
    }

    // work 模式
    @Test
    public void testWork() {
        for (int i = 0; i < 8; i++) {
            rabbitTemplate.convertAndSend("work", "work 模型:" + i);
        }
    }

    // hello world 模式
    @Test
    public void testHello() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }


}
