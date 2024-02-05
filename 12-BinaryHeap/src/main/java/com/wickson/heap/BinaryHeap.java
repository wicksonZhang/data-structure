package com.wickson.heap;

import com.wickson.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉堆
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {

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
     * @param element 元素
     */
    @Override
    public void add(E element) {
        elementCheck(element);
        // 扩容
        ensureCapacity(size + 1);
        elements[size++] = element;
        // 上滤
        siftUp(size - 1);
    }

    /**
     * 扩容
     *
     * @param capacity 容量
     */
    private void ensureCapacity(int capacity) {
        if (capacity - elements.length > 0) {
            int newCapacity = capacity + (capacity >> 1);
            E[] newElement = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElement[i] = elements[i];
            }
            elements = newElement;
        }
    }

    /**
     * 上滤
     *
     * @param index 索引
     */
    private void siftUp(int index) {
        // 获取到需要上滤的元素
        E element = elements[index];
        while (index > 0) {
            // 获取父级元素
            int parentIndex = index - 1 >> 2;
            E parentElement = elements[parentIndex];
            // 比较元素
            if (compare(parentElement, element) >= 0) {
                break;
            }
            // 这里和之前思路有点不同，我们直接找到需要替换元素的索引，直接将我们需要的值替换
            elements[index] = parentElement;
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void elementCheck(E e) {
        if (e == null) {
            throw new NullPointerException("Element is not null");
        }
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

    /**
     * 删除元素
     *
     * @return E
     */
    @Override
    public E remove() {
        emptyCheck();
        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;

        siftDown(0);
        return root;
    }

    /**
     * 下滤
     *
     * @param index
     */
    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        // 第一个叶子节点的索引 == 非叶子节点的数量
        // index < 第一个叶子节点的索引
        // 必须保证index位置是非叶子节点
        while (index < half) {
            // index的节点有2种情况
            // 1.只有左子节点
            // 2.同时有左右子节点

            // 默认为左子节点跟它进行比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;

            // 选出左右子节点最大的那个
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) break;

            // 将子节点存放到index位置
            elements[index] = child;
            // 重新设置index
            index = childIndex;
        }
        elements[index] = element;
    }


    /**
     * 删除堆顶元素同时插入一个新的元素
     *
     * @param element
     * @return
     */
    @Override
    public E replace(E element) {
        elementCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
}

