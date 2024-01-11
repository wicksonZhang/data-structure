package com.wickson.set;

import com.wickson.list.LinkedList;
import com.wickson.list.List;

/**
 * 通过双向链表实现集合
 *
 * @param <E>
 */
public class ListSet<E> implements Set<E> {

    private final List<E> list = new LinkedList<E>();

    /**
     * 元素数量
     *
     * @return int
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * 集合是否为空
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 清空元素
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * 集合是否包含某个元素
     *
     * @param element 元素
     * @return boolean
     */
    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    /**
     * 添加元素
     *
     * @param element 元素
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        int index = list.indexOf(element);
        if (index != -1) {
            list.set(index, element);
        } else {
            list.add(element);
        }
    }

    /**
     * 删除元素
     *
     * @param element 元素
     */
    @Override
    public void remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        int index = list.indexOf(element);
        if (index != -1) {
            list.remove(index);
        }
    }

    /**
     * 遍历集合
     *
     * @param visitor
     */
    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (visitor.visit(list.get(i))) {
                return;
            }
        }
    }
}
