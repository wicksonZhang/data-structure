package com.wickson.linkedlist.single;

import com.wickson.linkedlist.AbstractList;

/**
 * 链表
 */
public class SingleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    /**
     * 获取指定位置元素
     *
     * @param index 索引
     * @return
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
        E old = node.element;
        node.element = element;
        return old;
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

        if (index == 0) {
            first = new Node<>(element, null);
        } else {
            Node<E> prevNode = node(index - 1);
            Node<E> nextNode = prevNode.next;
            prevNode.next = new Node<>(element, nextNode);
        }
        size++;
    }

    /**
     * 删除指定元素
     *
     * @param index 索引
     * @return
     */
    @Override
    public E remove(int index) {
        rangeChecked(index);
        Node<E> node = node(index);
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> prevNode = node(index - 1);
            prevNode.next = prevNode.next.next;
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

    /**
     * 清空元素
     */
    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    /**
     * 节点
     *
     * @param <E>
     */
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

    /**
     * 获取节点
     *
     * @param index 索引
     * @return Node<E>
     */
    private Node<E> node(int index) {
        rangeChecked(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

}
