package com.wickson.map;

import java.util.List;

/**
 * 基于红黑树实现映射
 *
 * @param <K>
 * @param <V>
 */
public class TreeMap<K, V> implements Map<K, V> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containKey(K key) {
        return false;
    }

    @Override
    public boolean containValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }

    // 节点为红色
    private static final boolean RED = false;

    // 节点为黑色
    private static final boolean BLACK = true;

    /**
     * Node<K, V> 节点实现
     *
     * @param <K> key
     * @param <V> value
     */
    private static class Node<K, V> {
        // Key
        K key;

        // value
        V value;

        boolean color = RED;

        // 左子节点
        Node<K, V> leftNode;

        // 右子节点
        Node<K, V> rightNode;

        // 父级节点
        Node<K, V> parentNode;

        public Node(K key, V value, Node<K, V> parentNode) {
            this.key = key;
            this.value = value;
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
        public Node<K, V> sibling() {
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
