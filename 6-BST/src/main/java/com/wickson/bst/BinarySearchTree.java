package com.wickson.bst;

import java.util.Comparator;

/**
 * 二叉搜索树
 *
 * @author ZhangZiHeng
 * @date 2023-12-01
 */
public class BinarySearchTree<E> {

    private int size;

    private Node<E> root;

    private final Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
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

    // ========================================== 二叉搜索树 - 遍历 ==========================================


    /**
     * 删除指定位置元素
     *
     * @param element 元素
     */
    public void remove(E element) {

    }

    /**
     * 清除所有元素
     */
    public void clear() {

    }

    /**
     * 是否包含某个元素
     *
     * @param element 元素
     * @return
     */
    public boolean contains(E element) {
        return false;
    }

}
