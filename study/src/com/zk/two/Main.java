package com.zk.two;

/**
 * Package: com.zk.two
 * <p>
 * 第二章   运算和语句
 * 算术运算符
 * 赋值运算符
 * 比较运算符
 * 逻辑运算符
 * 位运算符
 * 三元运算符
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2022-09-05 18:02
 */
public class Main {
    public static void main(String[] args) {
        int i = 1;
        int sum = 0;
        do {
            sum += i;
            i++;
        } while (i <=100);
        System.out.println(sum);
    }
}
