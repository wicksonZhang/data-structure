package com.wickson.tree;

import com.wickson.set.Visitor;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 *
 * @author ZhangZiHeng
 * @date 2023-12-19
 */
public class BinaryTree<E> {

    /**
     * 节点数量
     */
    protected int size;

    /**
     * 根节点
     */
    protected Node<E> root;

    /**
     * 元素的数量
     *
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        root = null;
        size = 0;
    }

    //region ========================================== 二叉搜索树 - 遍历 ==========================================

    /**
     * 前序遍历：根节点、前序遍历左子树、前序遍历右子树
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println("node.element = " + node.element);
        preorderTraversal(node.leftNode);
        preorderTraversal(node.rightNode);
    }

    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        inorderTraversal(node.leftNode, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderTraversal(node.rightNode, visitor);
    }

    /**
     * 中序遍历：左子树、根节点、右子树
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.leftNode);
        System.out.println("node.element = " + node.element);
        inorderTraversal(node.rightNode);
    }

    /**
     * 后序遍历: 后续遍历左子树、后序遍历右子树、根节点
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.leftNode);
        postOrderTraversal(node.rightNode);
        System.out.println("node.element = " + node.element);
    }

    /**
     * 层序遍历：从上倒下，从左到右
     */
    public void levelOrderTraversal() {
        levelOrderTraversal(root);
    }

    private void levelOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        // 将根节点进行入队
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<E> newNode = queue.poll();
            System.out.print("newNode = " + newNode.element);
            if (node.leftNode != null) {
                queue.offer(node.leftNode);
            }
            if (node.rightNode != null) {
                queue.offer(node.rightNode);
            }
        }
    }

    //endregion ========================================== 二叉搜索树 - 遍历 ==========================================

    //region ========================================== 二叉搜索树 - 前驱和后继 ==========================================

    /**
     * 前驱节点
     *
     * @param node 节点
     */
    public Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 检查左子树的右节点
        if (root.leftNode != null) {
            Node<E> node1 = root.leftNode;
            while (node1.rightNode != null) {
                node1 = node1.rightNode;
            }
            return node1;
        }
        // 处理无左子树的情况
        Node<E> parentNode = node.parentNode;
        while (parentNode != null && node == parentNode.leftNode) {
            node = parentNode;
            parentNode = parentNode.parentNode;
        }

        return parentNode;
    }

    /**
     * 后继节点
     *
     * @param node 元素
     * @return Node<E>
     */
    public Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }

        // 处理存在右子树的情况
        if (root.rightNode != null) {
            Node<E> rightNode = root.rightNode;
            while (rightNode.leftNode != null) {
                rightNode = rightNode.leftNode;
            }
            return rightNode;
        }

        // 处理不存在右子树的情况
        Node<E> parentNode = node.parentNode;
        while (parentNode != null && node == parentNode.rightNode) {
            node = parentNode;
            parentNode = parentNode.parentNode;
        }
        return parentNode;
    }

    //endregion ========================================== 二叉搜索树 - 前驱和后继 ==========================================

    //region ========================================== 二叉搜索树 - 高度 ==========================================

    /**
     * 二叉树的高度
     *
     * @return int
     */
    public int height() {
        return height(root);
    }

    /**
     * 递归方式
     *
     * @param node 节点
     * @return
     */
//    private int height(Node<E> node) {
//        if (node == null) {
//            return 0;
//        }
//        return 1 + Math.max(height(node.leftNode), height(node.rightNode));
//    }

    /**
     * 非递归
     *
     * @param node 节点
     * @return int
     */
    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        // 树的高度
        int height = 0;
        // 当前层的数量
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            levelSize--;
            if (poll.leftNode != null) {
                queue.offer(poll.leftNode);
            }
            if (poll.rightNode != null) {
                queue.offer(poll.rightNode);
            }
            // 当层数等于0时，表示该层访问完了
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    //endregion ========================================== 二叉搜索树 - 高度 ==========================================

    /**
     * 创建节点
     *
     * @param element 元素信息
     * @param parent  父级节点信息
     * @return Node<E>
     */
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    /**
     * 初始化节点信息
     *
     * @param <E> 节点
     */
    protected static class Node<E> {
        E element;

        Node<E> rightNode;

        Node<E> leftNode;

        Node<E> parentNode;

        public Node(E element, Node<E> parentNode) {
            this.element = element;
            this.parentNode = parentNode;
        }

        /**
         * 是否存在叶子节点
         *
         * @return boolean
         */
        public boolean isLeaf() {
            return leftNode == null && rightNode == null;
        }

        /**
         * 是否该节点的度为2
         *
         * @return boolean
         */
        public boolean hasTwoChildren() {
            return leftNode != null && rightNode != null;
        }

        /**
         * 是否是左子树
         *
         * @return boolean
         */
        public boolean isLeftChild() {
            return parentNode != null && this == parentNode.leftNode;
        }

        /**
         * 是否是右子树
         *
         * @return boolean
         */
        public boolean isRightChild() {
            return parentNode != null && this == parentNode.rightNode;
        }

        /**
         * 兄弟节点
         *
         * @return Node<E>
         */
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parentNode.rightNode;
            }
            if (isRightChild()) {
                return parentNode.leftNode;
            }
            return null;
        }
    }

}
