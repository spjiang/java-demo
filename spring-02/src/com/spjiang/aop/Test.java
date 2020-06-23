package com.spjiang.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Package: com.spjiang.aop
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-23 15:24
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("aop.xml");
        MathI math = ac.getBean("mathImpl", MathI.class);
        int i = math.add(1, 1);
        System.out.println(i);

    }
}
