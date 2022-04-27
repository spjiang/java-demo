package org.example.refect;

/**
 * Package: org.example.refect
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-11-02 15:42
 */
public class GenericContainer<T> {
    private T obj;

    public GenericContainer() {
    }

    // Pass type in as parameter to constructor
    public GenericContainer(T t) {
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