package com.spjiang.springboot.rabbitmq.controller;

import com.spjiang.springboot.rabbitmq.annotation.Test01;
import com.spjiang.springboot.rabbitmq.bean.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: com.spjiang.springboot.rabbitmq.controller
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-01 11:53
 */
@RestController
@RequestMapping("/annotation")
public class AnnotationController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/test")
    @Test01(value = "spjiang")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        logger.info("test..s");
        return "ok";
    }
}
