package com.spjiang.springboot.bean;

import lombok.Data;

/**
 * Package: com.spjiang.springboot.bean
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-07 00:11
 */
@Data
public class Student {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
