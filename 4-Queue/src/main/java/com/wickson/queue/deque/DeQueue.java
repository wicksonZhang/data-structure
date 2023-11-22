package com.wickson.queue.deque;

import com.wickson.queue.list.LinkedList;
import com.wickson.queue.list.List;

/**
 * 双端队列
 */
public class DeQueue<E> {

    private List<E> linkedList = new LinkedList<>();

    /**
     * 元素数量
     *
     * @return int
     */
    public int size() {
        return linkedList.size();
    }

    /**
     * 是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /**
     * 从队尾入队
     *
     * @param element 元素
     */
    public void enQueueRear(E element) {
        linkedList.add(size(), element);
    }

    /**
     * 从队尾出队
     *
     * @return E
     */
    public E deQueueRear() {
        return linkedList.remove(size() - 1);
    }

    /**
     * 从队头入队
     *
     * @param element 元素
     */
    public void enQueueFront(E element) {
        linkedList.add(0, element);
    }

    /**
     * 从队头出队
     *
     * @return E
     */
    public E deQueueFront() {
        return linkedList.remove(0);
    }

    /**
     * 获取队列的头元素
     *
     * @return E
     */
    public E front() {
        return linkedList.get(0);
    }

    /**
     * 获取队列的尾元素
     *
     * @return E
     */
    public E rear() {
        return linkedList.get(size() - 1);
    }

}
