package com.wickson.linkedlist;

public interface List<E> {

    // 元素数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 是否包含某个元素
    boolean contains(E element);

    // 添加元素
    void add(E element);

    // 返回指定位置元素
    E get(int index);

    // 设置指定位置元素
    E set(int index, E element);

    // 将元素添加到指定位置
    void add(int index, E element);

    // 删除指定位置元素
    E remove(int index);

    // 查看元素指定位置
    int indexOf(E element);

    // 清除所有元素
    void clear();

}
