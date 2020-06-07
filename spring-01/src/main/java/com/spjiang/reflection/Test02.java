package com.spjiang.reflection;

/**
 * Package: com.spjiang.reflection
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-29 14:29
 */
public class Test02 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class cl = Class.forName("com.spjiang.reflection.pojo.User");
        Class c2 = Class.forName("com.spjiang.reflection.pojo.User");
        Class c3 = Class.forName("com.spjiang.reflection.pojo.User");
        System.out.println(cl);
        // 一个类在内存中只有一个对象，
        // 一个类被加载后，类的整个结构都会被封装在class对象中
        System.out.println(cl.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());

    }
}
