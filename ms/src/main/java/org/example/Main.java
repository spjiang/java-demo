package org.example;

import org.example.refect.ObjectContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: org.example
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-08-18 09:16
 */
public class Main {
    public static void main(String[] args) {
        ObjectContainer myObj = new ObjectContainer();
        // store a string
        myObj.setObj("Test");
        System.out.println("Value of myObj:" + myObj.getObj());
        // store an int (which is autoboxed to an Integer object)
        myObj.setObj(3);
        System.out.println("Value of myObj:" + myObj.getObj());

        List objectList = new ArrayList();
        objectList.add(myObj);
        // We have to cast and must cast the correct type to avoid ClassCastException!
        Integer myStr = (Integer) ((ObjectContainer) objectList.get(0)).getObj();
        System.out.println("myStr: " + myStr);
    }
}
