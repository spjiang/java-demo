package com.spjiang.springboot.rabbitmq.listener;

import com.spjiang.springboot.rabbitmq.bean.Order;
import com.spjiang.springboot.rabbitmq.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Package: com.spjiang.springboot.listener
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-27 17:37
 */
@Component
@Slf4j
public class RabbitmqListener {

    @RabbitListener(queues = {RabbitmqConfig.ORDER_QUEUE_NAME})
    public void orderDelayQueue(Message message) {
        Order order = (Order) message.getPayload();
        log.info("###########################################");
        log.info("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{}]- 【订单内容】 - [{}]", new Date(), order.toString());
        if (order.getOrderStatus() == 0) {
            order.setOrderStatus(2);
            log.info("【该订单未支付，取消订单】" + order.toString());
        } else if (order.getOrderStatus() == 1) {
            log.info("【该订单已完成支付】");
        } else if (order.getOrderStatus() == 2) {
            log.info("【该订单已取消】");
        }
        log.info("###########################################");
    }
}
