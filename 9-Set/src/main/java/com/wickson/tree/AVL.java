package com.wickson.tree;

import java.util.Comparator;

/**
 * AVL 树
 *
 * @author ZhangZiHeng
 * @date 2023-12-19
 */
public class AVL<E> extends BBST<E> {

    public AVL() {
        this(null);
    }

    public AVL(Comparator<? super E> comparator) {
        super(comparator);
    }

    /**
     * 添加之后处理
     *
     * @param node 节点信息
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parentNode) != null) {
            // 判断整棵树是否平衡，如果平衡，则更新高度，如果不平衡，则恢复平衡
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalanced(node);
                break;
            }
        }
    }

    /**
     * 删除之后做处理
     *
     * @param node 节点信息
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parentNode) != null) {
            // 判断整棵树是否平衡，如果平衡，则更新高度，如果不平衡，则恢复平衡
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalanced(node);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 判断是否平衡
     *
     * @param node 节点
     * @return boolean
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新节点高度
     *
     * @param node 节点
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /**
     * 重新恢复平衡
     *
     * @param grand 节点
     */
    private void rebalanced(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        // parent 是 grand 的左子树
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                rotateLeft(grand);
            }
        }
    }


    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * AVL 节点信息
     *
     * @param <E>
     */
    protected static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parentNode) {
            super(element, parentNode);
        }

        /**
         * 平衡因子
         *
         * @return int
         */
        public int balanceFactor() {
            int leftHeight = leftNode == null ? 0 : ((AVLNode<E>) leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>) rightNode).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新高度
         */
        public void updateHeight() {
            int leftHeight = leftNode == null ? 0 : ((AVLNode<E>) leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>) rightNode).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 高度比较高的子节点
         *
         * @return Node<E>
         */
        public Node<E> tallerChild() {
            int leftHeight = leftNode == null ? 0 : ((AVLNode<E>) leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>) rightNode).height;
            // 左子树高，就返回左子树
            if (leftHeight > rightHeight) return leftNode;
            // 右子树高，就返回右子树
            if (leftHeight < rightHeight) return rightNode;
            return isLeftChild() ? leftNode : rightNode;
        }

    }
}
