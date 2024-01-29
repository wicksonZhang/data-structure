package com.wickson.hash.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * HashMap 实现
 *
 * @param <K> Key
 * @param <V> Value
 */
public class HashMap<K, V> implements Map<K, V> {

    /**
     * 存储元素大小
     */
    private int size;

    private Node<K, V>[] table;

    /**
     * 1 << 4 : 00001 ==> 10000 = 16
     */
    private static final int DEFAULT_CAPACITY = 1 << 4;

    public HashMap() {
        this.table = new Node[DEFAULT_CAPACITY];
    }

    /**
     * 元素数量
     *
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空元素
     */
    @Override
    public void clear() {
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    /**
     * 添加元素
     *
     * @param key   Keu
     * @param value Value
     * @return V
     */
    @Override
    public V put(K key, V value) {
        int index = index(key);
        //Step-1：第一次添加元素
        Node<K, V> root = table[index];
        if (root == null) {
            root = new Node<>(key, value, null);
            table[index] = root;
            size++;
            afterPut(root);
            return null;
        }
        // Step2：如果当前节点不是父级节点，那就需要找到父级节点
        Node<K, V> node = root;
        Node<K, V> parent = root;
        int cmp = 0;
        int h1 = key == null ? 0 : key.hashCode();
        // 将传递进来的元素与父级节点进行比较
        do {
            cmp = compare(key, node.key, h1, node.hash);
            parent = node;
            if (cmp > 0) {
                node = node.rightNode;
            } else if (cmp < 0) {
                node = node.leftNode;
            } else {
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        } while (node != null);

        // Step3：我们将需要添加的元素添加在父级节点的那个位置
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.rightNode = newNode;
        } else {
            parent.leftNode = newNode;
        }
        size++;
        afterPut(newNode);
        return null;
    }

    /**
     * 通过 key 计算索引值
     * code ^ (code >>> 16) : 将结果进行低 16 位进行异或运算
     *
     * @param key Key
     * @return int
     */
    private int index(K key) {
        if (key == null) {
            return 0;
        }
        int code = key.hashCode();
        return (code ^ (code >>> 16)) & (table.length - 1);
    }

    private int index(Node<K, V> node) {
        return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
    }

    /**
     * 比较大小
     *
     * @param key1
     * @param key2
     * @param h1
     * @param h2
     * @return
     */
    @SuppressWarnings("unchecked")
    private int compare(K key1, K key2, int h1, int h2) {
        // 比较哈希值
        int result = h1 - h2;
        if (result != 0) {
            return result;
        }

        // 比较 equals
        if (Objects.equals(key1 , key2)) return 0;

        // 哈希值相等，但是不equals
        if (key1 != null && key2 != null) {
            String k1Class = key1.getClass().getName();
            String k2Class = key2.getClass().getName();
            result = k1Class.compareTo(k2Class);
            if (result != 0) {
                return result;
            }

            // 同一种类型具备可比较性
            if (key1 instanceof Comparable) {
                return ((Comparable) key1).compareTo(key2);
            }
        }

        return System.identityHashCode(key1) - System.identityHashCode(key2);
    }

    /**
     * Map 添加元素之后作的处理
     *
     * @param node 节点
     */
    private void afterPut(Node<K, V> node) {
        // 判断当前添加的父级节点
        Node<K, V> parentNode = node.parentNode;
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
        Node<K, V> uncle = parentNode.sibling();
        // 祖父节点
        Node<K, V> grandNode = red(parentNode.parentNode);
        if (isRed(uncle)) {
            // 将 parent 节点染成黑色
            black(parentNode);
            // 将 uncle 节点染成黑色
            black(uncle);
            // 将 grand 节点染成红色，向上进行合并，当作新添加的节点进行处理。
            afterPut(grandNode);
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

    /**
     * 判断红黑树节点的颜色
     *
     * @param node 节点
     * @return boolean
     */
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    /**
     * 判断是否是红色节点
     *
     * @param node 节点
     * @return boolean
     */
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    /**
     * 判断是否是红色节点
     *
     * @param node 节点
     * @return boolean
     */
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 对添加节点进行染色
     *
     * @param node  节点
     * @param color 颜色
     * @return Node<K, V>
     */
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return null;
        node.color = color;
        return node;
    }

    /**
     * 添加红色节点
     *
     * @param node 节点
     * @return Node<K, V>
     */
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    /**
     * 添加黑色节点
     *
     * @param node 节点
     */
    private void black(Node<K, V> node) {
        color(node, BLACK);
    }

    /**
     * 左旋转
     *
     * @param grand 节点
     */
    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.rightNode;
        Node<K, V> child = parent.leftNode;
        grand.rightNode = child;
        parent.leftNode = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 右旋转
     *
     * @param grand 节点
     */
    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.leftNode;
        Node<K, V> child = parent.rightNode;
        grand.leftNode = child;
        parent.rightNode = grand;
        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // 让 parent 成为子树的根节点
        parent.parentNode = grand.parentNode;
        if (grand.isLeftChild()) {
            grand.parentNode.leftNode = parent;
        } else if (grand.isRightChild()) {
            grand.parentNode.rightNode = parent;
        } else {
            table[index(grand)] = parent;
        }

        // 更新 child 的parent
        if (child != null) {
            child.parentNode = grand;
        }

        // 更新 grand 的 parent
        grand.parentNode = parent;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public boolean containKey(K key) {
        return node(key) != null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    private V remove(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        V oldValue = node.value;
        // 删除度为 2 的节点
        if (node.hasTwoChildren()) {
            // 找到当前元素的前驱节点
            Node<K, V> predecessorNode = successor(node);
            node.key = predecessorNode.key;
            node.value = predecessorNode.value;
            node = predecessorNode;
        }

        // 删除 node：node节点的度要么是1，要么是0
        Node<K, V> removeNode = node.leftNode != null ? node.leftNode : node.rightNode;

        int index = index(node);
        // 如果 node 是为删除度为1的节点
        if (removeNode != null) {
            // 更换父级节点
            removeNode.parentNode = node.parentNode;
            if (removeNode.parentNode == null) { // 当只存在根节点，根节点的度为1的情况
                table[index] = removeNode;
            } else if (node == node.parentNode.leftNode) {
                node.parentNode.leftNode = removeNode;
            } else {
                node.parentNode.rightNode = removeNode;
            }
            // 删除之后的处理
            afterRemove(removeNode);
        } else if (node.parentNode == null) { // 删除叶子节点, 且只有根节点元素
            table[index] = null;
            // 删除之后的处理
            afterRemove(node);
        } else { // 删除叶子节点, 有可能当前节点在父级节点的左边，也有可能在父级节点的右边
            if (node == node.parentNode.leftNode) {
                node.parentNode.leftNode = null;
            } else {
                node.parentNode.rightNode = null;
            }
            // 删除之后的处理
            afterRemove(node);
        }
        size--;
        return oldValue;
    }

    private void afterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parentNode;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.leftNode == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.rightNode : parent.leftNode;
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

    //region ========================================== 二叉搜索树 - 前驱和后继 ==========================================

    /**
     * 后继节点
     *
     * @param node 元素
     * @return Node<K, V>
     */
    public Node<K, V> successor(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        // 处理存在右子树的情况
        Node<K, V> rightNode = node.rightNode;
        if (rightNode != null) {
            while (rightNode.leftNode != null) {
                rightNode = rightNode.leftNode;
            }
            return rightNode;
        }

        // 处理不存在右子树的情况
        Node<K, V> parentNode = node.parentNode;
        while (parentNode != null && node == parentNode.rightNode) {
            node = parentNode;
            parentNode = parentNode.parentNode;
        }
        return parentNode;
    }

    //endregion ========================================== 二叉搜索树 - 前驱和后继 ==========================================


    private Node<K,V> node(K key) {
        // 首先，找到节点
        Node<K, V> node = table[index(key)];
        int h1 = key == null ? 0 : key.hashCode();
        while (node != null) {
            int compare = compare(key, node.key, h1, node.hash);
            if (compare == 0) {
                return node;
            }
            if (compare > 0) {
                node = node.rightNode;
            } else {
                node = node.leftNode;
            }
        }
        return null;
    }

    @Override
    public boolean containValue(V value) {
        if (size == 0) {
            return false;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (Node<K, V> kvNode : table) {
            if (kvNode == null) {
                continue;
            }
            queue.offer(kvNode);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(value, node.value)) {
                    return true;
                }

                if (node.leftNode != null) {
                    queue.offer(node.leftNode);
                }

                if (node.rightNode != null) {
                    queue.offer(node.rightNode);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) {
            return;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (Node<K, V> kvNode : table) {
            if (kvNode == null) {
                continue;
            }
            queue.offer(kvNode);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) {
                    return;
                }
                if (node.leftNode != null) {
                    queue.offer(node.leftNode);
                }
                if (node.rightNode != null) {
                    queue.offer(node.rightNode);
                }
            }
        }
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
        int hash;

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
            this.hash = key == null ? 0 : key.hashCode();
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
         * @return Node<K, V>
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
