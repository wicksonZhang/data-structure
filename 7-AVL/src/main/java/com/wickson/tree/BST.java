package com.wickson.tree;

import java.util.Comparator;

/**
 * 二叉搜索树
 *
 * @author ZhangZiHeng
 * @date 2023-12-01
 */
public class BST<E> extends BinaryTree<E> {

    private final Comparator<? super E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<? super E> comparator) {
        this.comparator = comparator;
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
            root = createNode(element, null);
            size++;
            // 新添加节点之后处理
            afterAdd(root);
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
        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.rightNode = newNode;
        } else {
            parent.leftNode = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    /**
     *
     * @param node
     */
    protected void afterAdd(Node<E> node) {

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
     * 是否包含某个元素
     *
     * @param element 元素
     * @return boolean
     */
    public boolean contains(E element) {
        return node(element) != null;
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


}
