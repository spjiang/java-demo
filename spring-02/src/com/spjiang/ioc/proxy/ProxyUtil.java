package com.spjiang.ioc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Package: com.spjiang.ioc.proxy
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-23 10:39
 */
public class ProxyUtil {
    private MathImpl mathImpl;

    public ProxyUtil(MathImpl mathImpl) {
        this.mathImpl = mathImpl;
    }

    public Object getProxy() {
        // 获取当前类的类加载器，用来加载代理对象所属类
        ClassLoader loader = this.getClass().getClassLoader();
        //  获取目标对象实现的接口的class，代理类会和目标类实现相同的接口，最终通过代理对象实现功能
        Class[] interfaces = mathImpl.getClass().getInterfaces();
        return Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
            // 代理对象实现功能的方式
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                try {
                    MyLogger.before(method.getName(), Arrays.toString(objects));
                    Object result = method.invoke(mathImpl, objects);
                    MyLogger.after(method.getName(), result);
                    System.out.println("后");
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                    MyLogger.throwing();
                } finally {
                    System.out.println("哪儿都有我...");
                }
                return null;
            }
        });
    }
}
