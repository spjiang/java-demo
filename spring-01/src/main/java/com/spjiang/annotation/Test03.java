package com.spjiang.annotation;

import java.lang.annotation.*;

/**
 * Package: com.spjiang.annotation
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-31 23:40
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Test03 {
    String name() default "";
}

