package com.wickson.bst.base;

import com.wickson.bst.list.LinkedList;
import com.wickson.bst.list.List;

/**
 * 普通队列
 *
 * @param <E>
 */
public class BaseQueue<E> {

    private List<E> list = new LinkedList<>();

    /**
     * 元素的数量
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
     * 清空元素
     */
    public void clear() {
        list.clear();
    }

    /**
     * 入队
     *
     * @param element 元素
     */
    public void enQueue(E element) {
        list.add(element);
    }

    /**
     * 出队
     *
     * @return E
     */
    public E deQueue() {
        return list.remove(0);
    }

    /**
     * 获取队列的头元素
     *
     * @return E
     */
    public E front() {
        return list.get(0);
    }

}
