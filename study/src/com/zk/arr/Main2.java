package com.zk.arr;

import java.util.Arrays;

/**
 * Package: com.zk.two
 * <p>
 * 第4章  数组和字符串
 * 选择排序
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2022-09-05 18:02
 */
public class Main2 {
    public static void main(String[] args) {
        int k, temp;
        // 升序
        int[] num = {7, 4, 9, 6, 2, 0, 1, 10, 5};
        for (int i = 0; i < num.length - 1; i++) {
            k = i;
            for (int j = k; j < num.length; j++) {
                // k是当前对比数据中最小值，j不断循环与之前循环中获取的最小k不断比较，获取最新最小的k对应的值
                if (num[j] < num[k]) {
                    k = j;
                }
            }
            // 当前循环中初始值下标
            temp = num[i];
            // k是当前循环最小值
            num[i] = num[k];
            num[k] = temp;
            System.out.println(Arrays.toString(num));
        }
    }

//    public static void main(String[] args) {
//        int k, temp;
//        // 升序
//        int[] num = {7, 4, 9, 6, 2, 0, 1};
//        for (int i = 0; i < num.length; i++) {
//            for (int j = i + 1; j < num.length; j++) {
//                if (num[j] < num[i]) {
//                    k = j;
//                    // 将最小的数据和第i个数交换
//                    temp = num[k];
//                    num[k] = num[i];
//                    num[i] = temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(num));
//    }

}
