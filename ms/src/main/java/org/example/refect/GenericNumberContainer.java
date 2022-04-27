package org.example.refect;

/**
 * Package: org.example.refect
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-11-05 09:13
 */
public class GenericNumberContainer<T extends Number> {
    private T obj;

    public GenericNumberContainer() {
    }

    public GenericNumberContainer(T t) {
        obj = t;
    }

    /**
     * @return the obj
     */
    public T getObj() {
        return obj;
    }

    /**
     * @param t the obj to set
     */
    public void setObj(T t) {
        obj = t;
    }
}
