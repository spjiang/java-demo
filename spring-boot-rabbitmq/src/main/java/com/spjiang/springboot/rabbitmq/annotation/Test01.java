package com.spjiang.springboot.rabbitmq.annotation;

import java.lang.annotation.*;

/**
 * Package: com.com.annotation
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-01 11:45
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Test01 {
    String value() default "";
}
