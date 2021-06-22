package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.rabbitmq.config
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-01-14 15:48
 */
@Configuration
public class RabbitmqConfig {
    @Value("${email.queue.name}")
    private String emailQueue;
    @Value("${exchange.name}")
    private String topicExchange;
    @Value("${dead.letter.queue.name}")
    private String deadLetterQueue;
    @Value("${dead.letter.exchange.name}")
    private String deadLetterExchange;

    /**
     * 绑定死信队列到正常队列中
     *
     * @return Queue
     */
    @Bean("emailQueue")
    public Queue emailQueue() {

        Map<String, Object> arguments = new HashMap<>(2);
        // 绑定死信交换机
        arguments.put("x-dead-letter-exchange", deadLetterExchange);
        // 绑定死信的路由key
        arguments.put("x-dead-letter-routing-key", deadLetterQueue + ".#");

        return new Queue(emailQueue, true, false, false, arguments);
    }

    /**
     * 声明交换机
     *
     * @return TopicExchange
     */
    @Bean("emailExchange")
    TopicExchange emailExchange() {
        return new TopicExchange(topicExchange, true, false);
    }

    /**
     * 队列绑定交换机
     *
     * @return Binding
     */
    @Bean
    Binding bindingEmailQueue() {
        return BindingBuilder.bind(emailQueue()).to(emailExchange()).with(emailQueue + ".#");
    }

    // 私信队列和交换器
    @Bean
    public Queue deadLetterQueue() {
        return new Queue(deadLetterQueue);
    }
    @Bean
    TopicExchange deadLetterExchange() {
        return new TopicExchange(deadLetterExchange);
    }

    @Bean
    Binding bindingDeadLetterQueue() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(deadLetterQueue+".#");
    }

}
