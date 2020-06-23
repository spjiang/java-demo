package com.spjiang.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
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
@Aspect
@Component
@Order(0) // 定义切面的优先级，值越小，优先级越高
public class TestAspect {
    @Pointcut(value = "execution(* com.spjiang.aop.*.*(..))")
    public void test() {

    }

    /**
     * 作用与方法之前
     *
     * @Before:将方法指定为前置通知 必须设置value, 将值为切入点表达式
     */
    // @Before(value = "execution(public int com.spjiang.aop.MathImpl.add(int,int))")
    //@Before(value = "execution(public int com.spjiang.aop.MathImpl.*(int,int))")
    //@Before(value = "execution(* com.spjiang.aop.MathImpl.*(int,int))")
    //@Before(value = "execution(* com.spjiang.aop.*.*(int,int))")
    @Before(value = "test()")
    public void beforeMethod(JoinPoint joinPoint) {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        System.out.println("TestAspect====>method:" + name + ",args:" + Arrays.toString(args));
    }
}
