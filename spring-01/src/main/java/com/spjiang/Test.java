package com.spjiang;

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
public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) ac.getBean("person");
        person.getName();
        System.out.println(person.getName());
    }
}
