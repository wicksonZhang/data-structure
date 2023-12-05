package com.wickson.bst.circle;

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
        ensureCapacity(size + 1);

        elements[index(size)] = element;
        size++;
    }

    /**
     * 获取下一个元素的索引
     *
     * @param index 索引
     * @return
     */
    private int index(int index) {
        // case1(使用 % 效率低): (front + index) % elements.length
        // case2: index - (elements.length > index ? 0 : elements.length);
        index += front;
        return index - (elements.length > index ? 0 : elements.length);
    }

    /**
     * 数组扩容
     *
     * @param capacity 当前容量
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int capacity) {
        if (capacity - elements.length > 0) {
            int newCapacity = capacity + (capacity >> 1);
            E[] newElement = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElement[i] = elements[index(i)];
            }
            elements = newElement;
            front = 0;
        }
    }

    /**
     * 出队
     *
     * @return E
     */
    public E deQueue() {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("This Circle is null");
        }
        E element = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return element;
    }

    /**
     * 获取队列的头元素
     */
    public E front() {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("This Circle is null");
        }
        return elements[front];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircleQueue [");
        for (int i = 0; i < size; i++) {
            sb.append(elements[index(i)]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
