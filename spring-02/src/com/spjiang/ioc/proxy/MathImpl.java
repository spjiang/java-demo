package com.spjiang.ioc.proxy;

/**
 * Package: com.spjiang.ioc.proxy
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-23 10:39
 */
public class MathImpl implements MathI {
    public int add(int i, int j) {
        int result = i + j;
        return result;
    }
}
