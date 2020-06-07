package com.spjiang.annotation;

import java.lang.annotation.*;

/**
 * Package: com.spjiang.annotation
 *
 * @description: 测试元注解
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-29 00:26
 */
public class Test02 {
    @MyAnnotation(name = "name")
    public void test() {

    }
}

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface MyAnnotation {
    String name() default "";
}