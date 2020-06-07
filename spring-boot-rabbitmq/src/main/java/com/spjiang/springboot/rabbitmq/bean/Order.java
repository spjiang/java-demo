package com.spjiang.springboot.rabbitmq.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Package: main.spjiang.springboot.bean
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-27 16:54
 */
@Data
public class Order implements Serializable {
    private String orderId;
    private String orderName;
    private int orderStatus;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
