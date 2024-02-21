package com.wickson.trie;

import java.util.HashMap;

/**
 * Trie 数据结构实现，用于存储字符串键和对应的值。
 *
 * @param <V> 值的类型参数
 * @author ZhangZiHeng
 * @date 2024-02-21
 */
public class Trie<V> {

    private int size;

    private Node<V> root;

    /**
     * 返回 Trie 中存储的键值对数量。
     *
     * @return Trie 中存储的键值对数量
     */
    public int size() {
        return size;
    }

    /**
     * 检查 Trie 是否为空。
     *
     * @return 如果 Trie 不包含任何键值对，则返回 true，否则返回 false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空 Trie，移除所有的键值对。
     */
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * 返回键对应的值。
     *
     * @param key 要检查的键
     * @return 如果 Trie 中包含键，则返回对应的值，否则返回 null
     */
    public V get(String key) {
        Node<V> node = node(key);
        return (node != null && node.isEndOfWord) ? node.value : null;
    }

    /**
     * 检查 Trie 是否包含给定的键。
     *
     * @param key 要检查的键
     * @return 如果 Trie 包含指定的键，则返回 true，否则返回 false
     */
    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.isEndOfWord;
    }

    /**
     * 检查 Trie 是否包含以给定前缀开头的任何键。
     *
     * @param prefix 要检查的前缀
     * @return 如果 Trie 包含以指定前缀开头的任何键，则返回 true，否则返回 false
     */
    public boolean startsWith(String prefix) {
        return node(prefix) != null;
    }

    /**
     * 返回键对应的节点。
     *
     * @param key 要检查的键
     * @return 键对应的节点，若键不存在，则返回 null
     */
    private Node<V> node(String key) {
        checkKey(key);

        Node<V> node = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (node == null || node.childNode == null || node.childNode.isEmpty()) {
                return null;
            }
            node = node.childNode.get(ch);
        }

        return node;
    }

    /**
     * 检查键的有效性。
     *
     * @param key 要检查的键
     */
    private void checkKey(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key cannot be empty");
        }
    }

    /**
     * 向 Trie 中添加指定的键值对。
     *
     * @param key   要添加的键
     * @param value 要添加的值
     */
    public void add(String key, V value) {
        checkKey(key);

        /* Step-1: 如果是第一次新增根节点为空, 则创建根节点 */
        if (root == null) {
            root = new Node<>(null);
        }

        /* Step-2: 遍历节点 */
        Node<V> node = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            Node<V> childNode = node.childNode == null ? null : node.childNode.get(ch);
            if (childNode == null) {
                childNode = new Node<>(node);
                childNode.character = ch;
                node.childNode = node.childNode == null ? new HashMap<>() : node.childNode;
                node.childNode.put(ch, childNode);
            }
            node = childNode;
        }

        /* Step-3: 如果 Key 已经存在，则返回旧值 */
        if (node.isEndOfWord) {
            node.value = value;
            return;
        }

        /* Step-4: 如果 Key 不存在，则重新赋值 */
        node.value = value;
        node.isEndOfWord = true;
        size++;
    }

    /**
     * 从 Trie 中移除指定的键及其关联的值。
     *
     * @param key 要移除的键
     * @return 如果键存在，则返回原值，否则返回 null
     */
    public V remove(String key) {
        /* Step-1: 找到最后一个字符 */
        Node<V> node = node(key);
        // 找不到 node 节点 并且 node 节点
        if (node == null || !node.isEndOfWord) {
            return null;
        }

        /* Step-2: 如果存在子节点 */
        V oldValue = node.value;
        if (node.childNode != null && !node.childNode.isEmpty()) {
            node.isEndOfWord = false;
            node.value = null;
            return oldValue;
        }

        /* Step-3: 如果不存在子节点 */
        // 找到最后一个节点的父级节点
        Node<V> parentNode;
        while ((parentNode = node.parentNode) != null) {
            // 找到父级节点删除对应的子节点
            parentNode.childNode.remove(node.character);
            // add、doggy
            if (parentNode.isEndOfWord || !parentNode.childNode.isEmpty()) {
                break;
            }
            node = parentNode;
        }

        size--;
        return oldValue;
    }

    /**
     * Trie 节点类，用于表示 Trie 树中的每个节点。
     *
     * @param <V> 值的类型参数
     */
    private static class Node<V> {

        // 父节点
        Node<V> parentNode;

        // 子节点信息，用于存储当前节点的子节点，键为字符，值为对应的子节点
        HashMap<Character, Node<V>> childNode;

        // 节点对应的字符
        Character character;

        // 节点对应的值
        V value;

        // 标记当前节点是否为单词的结束节点
        boolean isEndOfWord;

        public Node(Node<V> parentNode) {
            this.parentNode = parentNode;
        }
    }

}
