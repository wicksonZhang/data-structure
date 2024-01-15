package com.wickson.map;

/**
 * Map：Map<K, V>
 */
public interface Map<K, V> {

    int size(); // 元素数量

    boolean isEmpty(); // 集合是否为空

    void clear(); // 清除所有元素

    V put(K key, V value); // 添加元素

    V get(K key); // 获取元素

    V remove(K key); // 删除元素

    boolean containKey(K key); // 是否包含Key

    boolean containValue(V value); // 是否包含Value

    void traversal(Visitor<K, V> visitor); // 遍历集合

    public static abstract class Visitor<K, V> {
        public boolean stop;

        public abstract boolean visit(K key, V value);
    }

}
