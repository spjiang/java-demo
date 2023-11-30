package org.example;

/**
 * Package: org.example
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-08-18 09:16
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static String convertToDateString(long timestamp) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(timestamp * 1000L);
        String dateString = sdf.format(date);
        return dateString;
    }
    public static void main(String[] args) {
        System.out.println(convertToDateString(1672547612));
    }

}

