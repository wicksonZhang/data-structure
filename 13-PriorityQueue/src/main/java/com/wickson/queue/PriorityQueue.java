package com.wickson.queue;

import com.wickson.heap.BinaryHeap;

import java.util.Comparator;

/**
 * 优先级队列
 *
 * @author ZhangZiHeng
 * @date 2024-02-06
 */
public class PriorityQueue<E> {

    private final BinaryHeap<E> binaryHeap;

    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<E> comparator) {
        this.binaryHeap = new BinaryHeap<>(comparator);
    }

    /**
     * 元素的数量
     *
     * @return int
     */
    public int size() {
        return binaryHeap.size();
    }

    /**
     * 是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return binaryHeap.isEmpty();
    }

    /**
     * 清空元素
     */
    public void clear() {
        binaryHeap.clear();
    }

    /**
     * 入队
     *
     * @param element 元素
     */
    public void enQueue(E element) {
        binaryHeap.add(element);
    }

    /**
     * 出队
     *
     * @return E
     */
    public E deQueue() {
        return binaryHeap.remove();
    }

    /**
     * 获取队列的头元素
     *
     * @return E
     */
    public E front() {
        return binaryHeap.get();
    }

}
