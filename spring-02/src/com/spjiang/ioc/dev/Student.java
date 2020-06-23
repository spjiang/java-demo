package com.spjiang.ioc.dev;

/**
 * Package: com.spjiang
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-22 15:00
 */
public class Student {
    private String sname;
    private String age;

    @Override
    public String toString() {
        return "Student{" +
                "sname='" + sname + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
