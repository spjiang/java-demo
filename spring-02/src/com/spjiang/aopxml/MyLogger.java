package com.spjiang.aopxml;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Package: com.spjiang.aop
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-23 15:14
 */
// 标注当前类为切面
@Component
public class MyLogger {
    public void beforeMethod(JoinPoint joinPoint) {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        System.out.println("method:" + name + ",args:" + Arrays.toString(args));
    }
}
