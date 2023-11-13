package com.wickson.linkedlist.doubles;

import com.wickson.linkedlist.AbstractList;

/**
 * 双向链表
 */
public class LinkedList<E> extends AbstractList<E> {

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


    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public void clear() {

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
