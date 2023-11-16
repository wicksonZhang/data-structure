package com.wickson.linkedlist.cycle;

import com.wickson.linkedlist.AbstractList;

/**
 * 单向循环链表
 */
public class SingleCycleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    /**
     * 添加元素到指定位置
     *
     * @param index   索引
     * @param element 元素
     */
    @Override
    public void add(int index, E element) {
        rangeAddChecked(index);

        if (index == 0) { // 在首节点添加元素
            Node<E> node = new Node<>(element, first);
            first = node;
            if (size == 0) { // 当只有一个元素的情况
                node.next = node;
            } else {
                Node<E> lastNode = node(size - 1);
                lastNode.next = node;
            }
        } else { // 非首节点添加元素
            Node<E> prevNode = node(index - 1);
            prevNode.next = new Node<>(element, prevNode.next);
        }
        size++;
    }

    /**
     * 删除指定元素
     *
     * @param index 索引
     * @return E
     */
    public E remove(int index) {
        rangeChecked(index);
        Node<E> node = first;
        if (index == 0) {
            if (size == 1) {
                // case3: 当只有一个节点元素
                first = null;
            } else {
                // case2: 删除首节点元素
                first = node.next;
                Node<E> lastNode = node(size - 1);
                lastNode.next = first;
            }
        } else {
            // case1：往中间或者最后一个位置删除元素元素
            Node<E> prevNode = node(index - 1);
            Node<E> removeNode = prevNode.next;
            prevNode.next = removeNode.next;
            size--;
            return removeNode.element;
        }
        size--;
        return node.element;
    }


    /**
     * 获取元素索引
     *
     * @param element 元素
     * @return
     */
    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (null == node.element) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return -1;
    }

    /**
     * 打印元素
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ size = ").append(size).append(" , Node = [ ");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(node.element);
            node = node.next;
        }
        builder.append(" ]}");
        return builder.toString();
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    private Node<E> node(int index) {
        rangeChecked(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<E> {
        /**
         * 元素
         */
        E element;

        /**
         * 指向下一个节点
         */
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

}
