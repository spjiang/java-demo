package com.spjiang;

import com.spjiang.annotation.Test03;
import com.spjiang.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Package: com.spjiang
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-28 17:47
 */
public class Test2 {
    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.toString());
    }
}
