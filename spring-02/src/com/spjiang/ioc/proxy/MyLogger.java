package com.spjiang.ioc.proxy;

/**
 * Package: com.spjiang.ioc.proxy
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-23 14:31
 */
public class MyLogger {
    public static void before(String methodName, String args) {
        System.out.println("method:" + methodName + ",arguments:" + args);
    }

    public static void after(String methodName, Object result) {
        System.out.println("method:" + methodName + ",result:" + result);
    }

    public static void throwing() {
        System.out.println("有异常了...");
    }
}
