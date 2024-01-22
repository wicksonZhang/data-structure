package com.wickson.arraylist;

/**
 * 动态数组
 */
public class ArrayList<E> implements List<E> {

    // 动态数组的大小
    private int size;

    // 数组容量
    private E[] elements;

    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 默认初始容量为10
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 初始容量
     *
     * @param initialCapacity 初始容量
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        } else if (initialCapacity == 0) {
            elements = (E[]) new Object[]{};
        } else {
            elements = (E[]) new Object[initialCapacity];
        }
    }

    /**
     * 元素数量
     *
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 数组是否为空
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 数组是否包含某个元素
     *
     * @param e 元素
     * @return boolean
     */
    @Override
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    /**
     * 添加元素到首尾部
     *
     * @param element 元素
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * 获取指定位置元素
     *
     * @param index 元素下标
     * @return E
     */
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }

    /**
     * 设置指定位置元素
     *
     * @param index   索引
     * @param element 元素
     * @return E
     */
    @Override
    public E set(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * 添加到指定位置
     *
     * @param index   索引
     * @param element 元素
     */
    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
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
                newElement[i] = elements[i];
            }
            elements = newElement;
        }
    }

    /**
     * 删除元素
     *
     * @param index 索引
     * @return E
     */
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldElement = elements[index];
        for (int i = index + 1; i <= size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return oldElement;
    }

    /**
     * 获取元素索引
     *
     * @param e 元素
     * @return int
     */
    @Override
    public int indexOf(E e) {
        if (e == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(e)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        // 让数组中的元素为 null，等待 GC 回收
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 打印数组信息
     *
     * @return
     */
    public String printf() {
        StringBuilder builder = new StringBuilder();
        builder.append("ArrayList{ size = ")
                .append(size)
                .append(" , elements = [ ");
        for (int i = 0; i < size; i++) {
            builder.append(elements[i]);
            if (i != (size - 1)) {
                builder.append(" , ");
            }
        }
        builder.append(" ] }");
        return builder.toString();
    }
}
