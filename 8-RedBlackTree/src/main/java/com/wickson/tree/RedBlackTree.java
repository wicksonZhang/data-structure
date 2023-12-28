package com.wickson.tree;

import java.util.Comparator;

/**
 * 红黑树
 */
public class RedBlackTree<E> extends BST<E> {

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 对添加节点进行染色
     *
     * @param node  节点
     * @param color 颜色
     * @return Node<E>
     */
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return null;

        ((RedBlackNode<E>) node).color = color;
        return node;
    }

    /**
     * 判断红黑树节点的颜色
     *
     * @param node 节点
     * @return boolean
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RedBlackNode<E>) node).color;
    }

    /**
     * 判断是否是红色节点
     *
     * @param node 节点
     * @return boolean
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    /**
     * 判断是否是红色节点
     *
     * @param node 节点
     * @return boolean
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 添加红色节点
     *
     * @param node 节点
     * @return Node<E>
     */
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     * 添加黑色节点
     *
     * @param node 节点
     * @return Node<E>
     */
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    // 红色节点
    private static final boolean RED = false;

    // 黑色节点
    private static final boolean BLACK = true;

    /**
     * 红黑树节点
     *
     * @param <E>
     */
    private static class RedBlackNode<E> extends Node<E> {
        boolean color = RED;

        public RedBlackNode(E element, Node<E> parentNode) {
            super(element, parentNode);
        }
    }

}
