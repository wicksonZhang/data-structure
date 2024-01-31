package com.wickson.heap;

import java.util.Comparator;

/**
 * 二叉堆
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<E> implements Heap<E> {

    // 数组元素
    private E[] elements;

    // 元素数量
    private int size;

    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 1 << 4;

    // 二叉堆是具有可比较性的
    private final Comparator<E> comparator;

    public BinaryHeap() {
        this(null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * 二叉堆一定是具有可比较性的
     *
     * @param element1 元素1
     * @param element2 元素2
     * @return int
     */
    private int compare(E element1, E element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        }
        return ((Comparable<E>) element1).compareTo(element2);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 添加元素
     *
     * @param e 元素
     */
    @Override
    public void add(E e) {

    }

    /**
     * 获取堆顶元素
     *
     * @return E
     */
    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E e) {
        return null;
    }
}
