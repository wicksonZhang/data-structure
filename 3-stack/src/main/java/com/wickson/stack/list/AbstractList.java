package com.wickson.stack.list;

public abstract class AbstractList<E> implements List<E> {

    protected int size = 0;

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
     * 是否为空
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素
     *
     * @param element 元素
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * 是否包含某个元素
     *
     * @param element 元素
     * @return boolean
     */
    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    /**
     * 索引范围检查
     *
     * @param index 索引
     */
    public void rangeChecked(int index) {
        if (index >= size || index < 0) {
            indexOutOfBounds(index);
        }
    }

    /**
     * 新增元素索引范围检查
     *
     * @param index 索引
     */
    public void rangeAddChecked(int index) {
        if (index > size || index < 0) {
            indexOutOfBounds(index);
        }
    }

    /**
     * 数组越界
     *
     * @param index 索引
     */
    public void indexOutOfBounds(int index) { throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size); }
}
