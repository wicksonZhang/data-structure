package com.wickson.bst;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树
 *
 * @author ZhangZiHeng
 * @date 2023-12-01
 */
public class BinarySearchTree<E> {

    private int size;

    private Node<E> root;

    private final Comparator<? super E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 初始化节点信息
     *
     * @param <E> 节点
     */
    private static class Node<E> {
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
    }

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
     * 添加元素
     *
     * @param element 元素
     */
    public void add(E element) {
        // 参数检查
        checkedElementNotNull(element);

        // Step1：如果当前的父级节点为 null ，则代表添加第一个元素
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // Step2：如果当前节点不是父级节点，那就需要找到父级节点
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        // 将传递进来的元素与父级节点进行比较
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.rightNode;
            } else if (cmp < 0) {
                node = node.leftNode;
            } else {
                node.element = element;
                return;
            }
        }

        // Step3：我们将需要添加的元素添加在父级节点的那个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.rightNode = newNode;
        } else {
            parent.leftNode = newNode;
        }
        size++;
    }

    /**
     * 比较两个元素的大小
     * Comparator: java.util.Comparator
     * Comparable: java.lang.Comparable
     *
     * @param element1 元素1
     * @param element2 元素2
     * @return int
     */
    @SuppressWarnings("unchecked")
    private int compare(E element1, E element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        }
        return ((Comparable<E>) element1).compareTo(element2);
    }

    /**
     * 检查参数不为空
     *
     * @param element 元素
     */
    private void checkedElementNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    // ========================================== 二叉搜索树 - 遍历 ==========================================

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

    // ========================================== 二叉搜索树 - 遍历 ==========================================


    // ========================================== 二叉搜索树 - 前驱和后继 ==========================================

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

    // ========================================== 二叉搜索树 - 前驱和后继 ==========================================

    // ========================================== 二叉搜索树 - 高度 ==========================================

    /**
     * 二叉树的高度
     *
     * @return int
     */
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.leftNode), height(node.rightNode));
    }


    // ========================================== 二叉搜索树 - 高度 ==========================================

    /**
     * 删除指定位置元素
     *
     * @param element 元素
     */
    public void remove(E element) {
        checkedElementNotNull(element);

        Node<E> node = node(element);
        remove(node);
    }

    /**
     * 删除元素
     *
     * @param node 节点
     */
    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        // 删除度为 2 的节点
        if (node.hasTwoChildren()) {
            // 找到当前元素的前驱节点
            Node<E> predecessorNode = predecessor(node);
            node.element = predecessorNode.element;
            node = predecessorNode;
        }

        // 删除 node：node节点的度要么是1，要么是0
        Node<E> removeNode = node.leftNode != null ? node.leftNode : node.rightNode;

        // 如果 node 是为删除度为1的节点
        if (removeNode != null) {
            // 更换父级节点
            removeNode.parentNode = node.parentNode;
            if (removeNode.parentNode == null) { // 当只存在根节点，根节点的度为1的情况
                root = removeNode;
            } else if (node == node.parentNode.leftNode) {
                node.parentNode.leftNode = removeNode;
            } else {
                node.parentNode.rightNode = removeNode;
            }
        } else if (node.parentNode == null) { // 删除叶子节点, 且只有根节点元素
            root = null;
        } else { // 删除叶子节点, 有可能当前节点在父级节点的左边，也有可能在父级节点的右边
            if (node == node.parentNode.leftNode) {
                node.parentNode.leftNode = null;
            } else {
                node.parentNode.rightNode = null;
            }
        }
        size--;
    }

    /**
     * 获取元素的节点
     *
     * @param element 元素
     * @return E
     */
    private Node<E> node(E element) {
        Node<E> node = this.root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.rightNode;
            } else if (cmp < 0) {
                node = node.leftNode;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 是否包含某个元素
     *
     * @param element 元素
     * @return boolean
     */
    public boolean contains(E element) {
        return node(element) != null;
    }

}
