package com.spjiang.ioc.proxy;

/**
 * Package: com.spjiang
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-22 15:02
 */
public class Test {
    public static void main(String[] args) {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("scope.xml");
        ProxyUtil proxy = new ProxyUtil(new MathImpl());
        MathI math = (MathI) proxy.getProxy();
        int i = math.add(1, 1);
        System.out.println(i);
    }
}
