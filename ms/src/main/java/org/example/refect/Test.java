package org.example.refect;

import java.util.List;

/**
 * Package: org.example.refect
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-11-05 11:23
 */
public class Test {
    public static <T> void checkList(List<?> myList, T obj){
        if(myList.contains(obj)){
            System.out.println("The list contains the element: " + obj);
        } else {
            System.out.println("The list does not contain the element: " + obj);
        }
    }

}
