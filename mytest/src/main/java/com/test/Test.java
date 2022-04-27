package com.test;

import com.ctfo.protocol.support.utils.ConvUtil;

/**
 * Package: com.test
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-17 16:21
 */
public class Test {
    public static void main(String[] args) {
        byte[] data = {};
        data = new byte[10];
        System.out.println(data.length);

        ConvUtil.bytes2Hex(data);
    }
}
