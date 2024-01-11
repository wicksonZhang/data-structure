package com.wickson.set;

public abstract class Visitor<E> {

    public boolean stop;

    /**
     * @return 如果返回true，就代表停止遍历
     */
    public abstract boolean visit(E element);
}