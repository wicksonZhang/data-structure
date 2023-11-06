package com.wickson.linkedlist;

/**
 * 链表
 */
public class LinkedList<E> extends AbstractList<E> {

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

    @Override
    public int indexOf(E element) {
        return 0;
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
