package com.wickson.queue.circle;

/**
 * 循环队列
 */
@SuppressWarnings("unchecked")
public class CircleQueue<E> {

    // 数组大小
    private int size;

    // 头元素的索引
    private int front;

    // 数组
    private E[] elements;

    // 数组容量
    private static final int DEFAULT_CAPACITY = 10;

    // 初始化构造方法
    public CircleQueue() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * 元素的数量
     *
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * 队列是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队
     *
     * @param element 元素
     */
    public void enQueue(E element) {
        elements[size] = element;
        size++;
    }

    /**
     * 出队
     *
     * @return E
     */
    public E deQueue() {
        return null;
    }

    /**
     * 获取队列的头元素
     */
    public E front() {
        return elements[front];
    }

}
