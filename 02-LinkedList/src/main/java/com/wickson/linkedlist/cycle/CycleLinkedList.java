package com.wickson.linkedlist.cycle;

import com.wickson.linkedlist.AbstractList;

public class CycleLinkedList<E> extends AbstractList<E> {

    /**
     * 指向上一个节点
     */
    private Node<E> first;

    /**
     * 指向下一个节点
     */
    private Node<E> last;

    /**
     * Node 节点
     *
     * @param <E> 元素
     */
    public static class Node<E> {
        E element;

        Node<E> prev;

        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 获取指定位置元素
     *
     * @param index 索引
     * @return E
     */
    @Override
    public E get(int index) {
        return node(index).element;
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
        if (index == size) { // case2: 往尾节点添加元素
            Node<E> lastNode = last;
            last = new Node<>(lastNode, element, first);
            if (lastNode == null) { // case3: 当只有一个元素的情况
                first = last;
                first.prev = first;
                first.next = first;
            } else {
                lastNode.next = last;
                first.prev = last;
            }
        } else {
            // case1: 往中间位置添加元素
            Node<E> nextNode = node(index);
            Node<E> prevNode = nextNode.prev;
            Node<E> node = new Node<>(prevNode, element, nextNode);
            prevNode.next = node;
            nextNode.prev = node;
            // case4: index = 0, size = 1
            if (nextNode == last) {
                last = node;
            }
        }
        size++;
    }

    /**
     * 删除指定元素
     *
     * @param index 索引
     * @return E
     */
    @Override
    public E remove(int index) {
        rangeChecked(index);
        Node<E> node = first;
        // case2: 当只存在一个元素
        if (size == 1) {
            first = null;
            last = null;
        } else {
            // case1：删除中间元素
            node = node(index);
            Node<E> prevNode = node.prev;
            Node<E> nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            // case3: index = 0, size = 2
            if (node == first) {
                first = nextNode;
            }
            // case4: index = 1, size = 2
            if (node == last) {
                last = prevNode;
            }
        }
        size--;
        return node.element;
    }

    /**
     * 获取元素索引
     *
     * @param element 元素
     * @return int
     */
    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
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
     * 清空元素
     */
    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
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

    /**
     * 获取 Node 节点
     *
     * @param index 索引
     * @return Node<E>
     */
    private Node<E> node(int index) {
        // 检查索引范围
        rangeChecked(index);

        Node<E> node;
        if ((size >> 1) > index) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }
}
