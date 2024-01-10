package com.wickson.tree;

import java.util.Comparator;

/**
 * 红黑树
 */
public class RedBlackTree<E> extends BBST<E> {

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
     * @param node 节点信息
     */
    @Override
    protected void afterAdd(Node<E> node) {
        // 判断当前添加的父级节点
        Node<E> parentNode = node.parentNode;
        if (parentNode == null) {
            black(node);
            return;
        }

        /* Step-1：添加的节点父节点正好是 black */
        if (isBlack(parentNode)) {
            return;
        }

        /* Step-2：添加的节点 `uncle` 节点是 `Red` */
        // 叔父节点
        Node<E> uncle = parentNode.sibling();
        // 祖父节点
        Node<E> grandNode = parentNode.parentNode;
        if (isRed(uncle)) {
            // 将 parent 节点染成黑色
            black(parentNode);
            // 将 uncle 节点染成黑色
            black(uncle);
            // 将 grand 节点染成红色，向上进行合并，当作新添加的节点进行处理。
            afterAdd(red(grandNode));
            return;
        }

        /* Step-3：添加的节点 `uncle` 节点是 `Black` */
        // 判断父级节点是否是左子节点
        if (parentNode.isLeftChild()) { // L
            // 将 `grand` 节点染成红色
            red(grandNode);
            // 判断自己是否是左子节点
            if (node.isLeftChild()) { // LL
                // 将 `parent` 节点染成黑色
                black(parentNode);
            } else { // LR
                // 将 `parent` 节点染成黑色
                black(node);
                // 将 `parent` 进行左旋转，将二叉树变为 `LL`
                rotateLeft(parentNode);
            }
            // 将 `grand` 节点进行右旋
            rotateRight(grandNode);
        } else { // R
            // 将 `grand` 节点染成红色
            red(grandNode);
            if (node.isRightChild()) { // RR
                // 将 `parent` 节点染成黑色
                black(parentNode);
            } else { // RL
                // 将 `node` 节点染成黑色
                black(node);
                // 将 `parent` 进行右旋转，将二叉树变为 `RR`
                rotateRight(parentNode);
            }
            // 将 `grand` 进行左旋转，使二叉树变得平衡
            rotateLeft(grandNode);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        /* Step-1：如果叶子节点为红色，不用处理 */
//        if (isRed(node)) return;

        /* Step-2：1 个 Red 子节点的 Black 节点 */
        if (isRed(node)) {
            black(node);
            return;
        }

        // 删除的节点是根节点
        Node<E> parentNode = node.parentNode;
        if (parentNode == null) { return; }

        /* Step-3：叶子节点 Black 节点 */
        // leftNode == null =》 leftNode 就是被删除的节点
        boolean leftNode = parentNode.leftNode == null || node.isLeftChild();
        Node<E> siblingNode = leftNode ? parentNode.rightNode : parentNode.leftNode;
        if (leftNode) { // 被删除的节点在左边，兄弟节点在右边
            // 兄弟节点是红色
            if (isRed(siblingNode)) {
                black(siblingNode);
                red(parentNode);
                rotateLeft(parentNode);
                // 更换兄弟节点
                siblingNode = parentNode.rightNode;
            }
            // 兄弟节点必然是黑色
            if (isBlack(siblingNode.leftNode) && isRed(siblingNode.rightNode)) {
                // 兄弟节点没有 1 个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parentNode);
                black(parentNode);
                red(siblingNode);
                if (parentBlack) {
                    afterRemove(parentNode);
                }
            } else {
                // 兄弟节点至少有 1 个红色子节点，向兄弟节点借元素
                if (isBlack(siblingNode.rightNode)) {
                    rotateRight(siblingNode);
                    siblingNode = parentNode.rightNode;
                }

                color(siblingNode, colorOf(parentNode));
                black(siblingNode.rightNode);
                black(parentNode);
                rotateLeft(parentNode);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            // 兄弟节点是红色
            if (isRed(siblingNode)) {
                black(siblingNode);
                red(parentNode);
                rotateRight(parentNode);
                // 更换兄弟节点
                siblingNode = parentNode.leftNode;
            }
            // 兄弟节点必然是黑色
            if (isBlack(siblingNode.leftNode) && isRed(siblingNode.rightNode)) {
                // 兄弟节点没有 1 个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parentNode);
                black(parentNode);
                red(siblingNode);
                if (parentBlack) {
                    afterRemove(parentNode);
                }
            } else {
                // 兄弟节点至少有 1 个红色子节点，向兄弟节点借元素
                if (isBlack(siblingNode.leftNode)) {
                    rotateLeft(siblingNode);
                    siblingNode = parentNode.leftNode;
                }

                color(siblingNode, colorOf(parentNode));
                black(siblingNode.leftNode);
                black(parentNode);
                rotateRight(parentNode);
            }
        }

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
     * 创建红黑树节点
     *
     * @param element 元素信息
     * @param parent  父级节点信息
     * @return Node<E>
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RedBlackNode<>(element, parent);
    }

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
