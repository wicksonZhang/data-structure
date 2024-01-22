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
        Node<E> grandNode = red(parentNode.parentNode);
        if (isRed(uncle)) {
            // 将 parent 节点染成黑色
            black(parentNode);
            // 将 uncle 节点染成黑色
            black(uncle);
            // 将 grand 节点染成红色，向上进行合并，当作新添加的节点进行处理。
            afterAdd(grandNode);
            return;
        }

        /* Step-3：添加的节点 `uncle` 节点是 `Black` */
        // 判断父级节点是否是左子节点
        if (parentNode.isLeftChild()) { // L
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
            if (node.isLeftChild()) { // RL
                // 将 `node` 节点染成黑色
                black(node);
                // 将 `parent` 进行右旋转，将二叉树变为 `RR`
                rotateRight(parentNode);
            } else { // RR
                // 将 `parent` 节点染成黑色
                black(parentNode);
            }
            // 将 `grand` 进行左旋转，使二叉树变得平衡
            rotateLeft(grandNode);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parentNode;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.leftNode == null || node.isLeftChild();
        Node<E> sibling = left ? parent.rightNode : parent.leftNode;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.rightNode;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.leftNode) && isBlack(sibling.rightNode)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.rightNode)) {
                    rotateRight(sibling);
                    sibling = parent.rightNode;
                }

                color(sibling, colorOf(parent));
                black(sibling.rightNode);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.leftNode;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.leftNode) && isBlack(sibling.rightNode)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.leftNode)) {
                    rotateLeft(sibling);
                    sibling = parent.leftNode;
                }

                color(sibling, colorOf(parent));
                black(sibling.leftNode);
                black(parent);
                rotateRight(parent);
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
