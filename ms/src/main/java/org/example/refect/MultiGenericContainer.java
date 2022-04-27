package org.example.refect;

/**
 * Package: org.example.refect
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-11-02 18:06
 */
public class MultiGenericContainer<T, S> {
    private T firstPosition;
    private S secondPosition;

    public MultiGenericContainer(T firstPosition, S secondPosition){
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    public T getFirstPosition(){
        return firstPosition;
    }

    public void setFirstPosition(T firstPosition){
        this.firstPosition = firstPosition;
    }

    public S getSecondPosition(){
        return secondPosition;
    }

    public void setSecondPosition(S secondPosition){
        this.secondPosition = secondPosition;
    }

}