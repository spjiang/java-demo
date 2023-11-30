package com.zk.arr;

import java.util.Arrays;

/**
 * Package: com.zk.two
 * <p>
 * 第4章  数组和字符串
 * 冒泡排序
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2022-09-05 18:02
 */
public class Main {
    public static void main(String[] args) {
        // 冒泡排序(升序)
        // +++++ i=0,j<4 ++++++
        // {2,10,12,7,1} j=0
        // {2,10,12,7,1} j=1
        // {2,10,7,12,1} j=2
        // {2,10,7,1,12} j=3

        // +++++ i=1,j<3 ++++++
        // {2,10,7,1,12} j=0
        // {2,7,10,1,12} j=1
        // {2,7,1,10,12} j=2

        // +++++ i=2,j<2 ++++++
        // {2,7,1,10,12} j=0
        // {2,1,7,10,12} j=1

        // +++++ i=3,j<1 ++++++
        // {1,2,7,10,12} j=0
        int[] test = {10, 2, 12, 7, 1};
        for (int i = 0; i < test.length - 1; i++) {
            for (int j = 0; j < test.length - 1 - i; j++) {
                if (test[j] > test[j + 1]) {
                    int tmp = test[j];
                    test[j] = test[j + 1];
                    test[j + 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(test));
    }
}
