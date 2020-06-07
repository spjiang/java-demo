package com.spjiang.springboot.rabbitmq;

import com.spjiang.springboot.rabbitmq.service.DelaySender;
import com.spjiang.springboot.rabbitmq.bean.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = SpringBootRabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class RabbitmqDelayTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DelaySender delaySender;

    @Test
    public void test() {
        Order order = new Order();
        order.setOrderId("1");
        order.setOrderName("订单名称");
        order.setOrderStatus(0);
        delaySender.sendDelay(order);
    }

}
