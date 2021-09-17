package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Package: com.file
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-17 14:20
 */
public class app {
    public static void main(String[] args) {
        // 先把图片读入到内存--然后写到 文件
        FileInputStream fis = null;
        File q = new File("/Users/jiangshengping/parking.log");
        // 因为File没有读写的能力，所以需要使用InputStream；
        try {
            fis = new FileInputStream(q);
            // 定义一个字节数组,相当于缓存
            byte[] bytes = new byte[1024];
            // 得到实际读取到的字节数 读到最后返回-1
            int n = 0;
            // 循环读取
            // 把fis里的东西读到bytes数组里去
            while ((n = fis.read(bytes)) != -1) {
                System.out.println("++++" + n);
                //把字节转成String 从0到N变成String
                String w = new String(bytes, 0, n);
                // System.out.println(w);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 一定要关闭文件流。并且关闭文件流必须放在finally里面
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}

