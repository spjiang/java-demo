package com.spjiang.reflection;

import com.spjiang.reflection.pojo.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Package: com.spjiang.reflection
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-29 14:29
 */
public class Test03 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 获取class 对象
        Class cl = Class.forName("com.spjiang.reflection.pojo.User");
        // 打印class 对象
        System.out.println(cl);
        // 一个类在内存中只有一个对象，
        // 一个类被加载后，类的整个结构都会被封装在class对象中
        // 打印class code
        System.out.println(cl.hashCode());

        // 生成一个user对象
        User user = (User) cl.newInstance();

        Method test = cl.getDeclaredMethod("test", user.getClass());

        // 调用方法
        test.invoke(user, "spjiang come on");
    }
}
