package com.wickson.stack;

import com.wickson.stack.list.ArrayList;
import com.wickson.stack.list.List;

/**
 * 栈
 */
public class Stack<E> {

    private final List<E> list = new ArrayList<>();

    /**
     * 元素数量
     *
     * @return int
     */
    public int size() {
        return list.size();
    }

    /**
     * 是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 入栈
     *
     * @param element 元素
     */
    public void push(E element) {
        list.add(element);
    }

    /**
     * 出栈
     *
     * @return E
     */
    public E pop() {
        return list.remove(list.size() - 1);
    }

    /**
     * 获取栈顶元素
     *
     * @return E
     */
    public E top() {
        return list.get(list.size() - 1);
    }

}
