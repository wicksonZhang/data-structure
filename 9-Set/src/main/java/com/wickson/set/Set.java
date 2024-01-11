package com.wickson.set;


/**
 * Set 集合接口
 */
public interface Set<E> {

    // 集合元素数量
    int size();

    // 集合是否为空
    boolean isEmpty();

    // 清除所有元素
    void clear();

    // 集合是否包含某个元素
    boolean contains(E element);

    // 添加元素
    void add(E element);

    // 删除元素
    void remove(E element);

    // 遍历集合
    void traversal(Visitor<E> visitor);

}
