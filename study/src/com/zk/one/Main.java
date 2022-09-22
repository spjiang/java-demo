package com.zk.one;

/**
 * Package: com.zk
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2022-09-05 17:41
 */
public class Main {
    public static void main(String[] args) {
        //int转换为String
        // String s = String.valueOf(i);
        // String s = Integer.toString(i)
        // String s = ""+i;
        // 将String转换成int
        // int i = Integer.parseInt(String);
        int i = Integer.valueOf("5").intValue();
        System.out.println(i);

    }
}
