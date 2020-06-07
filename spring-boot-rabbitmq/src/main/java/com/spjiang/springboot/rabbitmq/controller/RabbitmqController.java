package com.spjiang.springboot.rabbitmq.controller;

import com.spjiang.springboot.rabbitmq.bean.Order;
import com.spjiang.springboot.rabbitmq.service.DelaySender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: main.spjiang.springboot.controller
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-27 18:17
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DelaySender delaySender;

    @PostMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        Order order = new Order();
        order.setOrderId("1");
        order.setOrderName("订单名称");
        order.setOrderStatus(0);
        delaySender.sendDelay(order);
        return "ok";
    }

}
