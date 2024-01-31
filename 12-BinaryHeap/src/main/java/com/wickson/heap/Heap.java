package com.wickson.heap;

/**
 * 堆
 *
 * @param <E>
 */
public interface Heap<E> {

    // 元素的数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 清空元素
    void clear();

    // 添加元素
    void add(E e);

    // 获取堆顶元素
    E get();

    // 删除堆顶元素
    E remove();

    // 替换元素
    E replace(E e);

}
