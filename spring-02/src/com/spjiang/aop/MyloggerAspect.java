package com.spjiang.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
public class MyloggerAspect {
    @Pointcut(value = "execution(* com.spjiang.aop.*.*(..))")
    public void test(){

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
        System.out.println("method:" + name + ",args:" + Arrays.toString(args));
    }

    /**
     * 场景：一般都是关闭一些资源.
     * <p>
     * 后置通知..
     * 作用于方法的finally语句块，即不管有没有异常都要执行
     *
     * @param joinPoint
     */
    @After(value = "test()")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("后置通知...");
    }

    /**
     * 返回通知
     * 作用于方法执行之后
     * 方法有异常就不能执行
     * 可通过returning设置接收方法返回值的变量名
     *
     * @param joinPoint
     */
    @AfterReturning(value = "test()", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        System.out.println("method:" + name + ",args:" + Arrays.toString(args) + ",result:" + result);
        System.out.println("返回通知...");
    }

    /**
     * 异常通知（例外通知）
     * 作用于方法抛出异常时
     * 通过throwing设置接收方法返回的异常信息
     * 在参数列表中可通过具体的异常类型，来对指定的异常信心进行操作
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "execution(* com.spjiang.aop.*.*(..))", throwing = "ex")
    public void AfterThrowingMethod(JoinPoint joinPoint, Exception ex) {
        System.out.println("异常通知,message:" + ex);
    }

    /**
     * 环绕通知
     */
    @Around(value = "execution(* com.spjiang.aop.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            // 前置通知
            System.out.println("前置通知");
            // 执行方法
            Object result = proceedingJoinPoint.proceed();
            System.out.println("返回通知");
            return result;
            // 返回通知
        } catch (Throwable throwable) {
            System.out.println("异常通知");
            throwable.printStackTrace();
            // 异常通知
        } finally {
            System.out.println("后置通知");
            //  后置通知
        }
        return -1;
    }
}
