# Trie

> 本章节代码：

## 基础知识

### Trie 解决了什么问题？

Trie：**主要解决了字符串的检索和前缀匹配问题**，可以在 O(m) 的时间复杂度内检索具有特定前缀的字符串集合，其中 m 是要搜索的字符串的长度。

例如，我们可以借助 Trie 数据结构来实现自动补全、拼写检查等等。如下我们在 Google 浏览器中输入 `eng` 下面则会出现对应的单词进行补全。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202402210915124.png" alt="image-20240221091557071" style="zoom:100%;float:left" />

### Trie 是什么？

Trie（也称为 前缀树 或 字典树 ）是一种树形数据结构，用于有效地存储和检索字符串数据集中的键值。

例如，我们需要存储 `cat`、`dog`、`doggy`、`does`、`cast`、`add` 这六个单词，使用 Tire 存储的数据格式如下：

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202402210923984.png" alt="image-20240221092328944" style="zoom:80%;float:left;" />

### Trie 优缺点

**优点**

1. **高效的前缀匹配**：Trie 具有高效的前缀匹配能力，可以在 O(m) 的时间复杂度内检索具有特定前缀的字符串集合，其中 m 是要搜索的字符串的长度。

**缺点**

1. **空间复杂度高**：Trie 数据结构会大量的占用内存，造成空间的浪费。
2. **不适合存储大字符集**：当字符集很大时，比如 Unicode 字符集，Trie 的存储和遍历操作可能会变得复杂和低效。



## 具体实现

### 接口设计

```java
public class Trie<V> {

    // 元素数量
    public int size() { return 0; }

    // 是否为空
    public boolean isEmpty() { return false; }

    // 清空 Trie
    public void clear() { }

    // 返回键对应的值
    public V get(String key) { return null; }
        
    // 是否包含给定的键
    public boolean contains(String key) { return false; }

    // 是否以给定前缀开头
    public boolean starsWith(String prefix) { return false; }
    
    // 添加指定的键值对
    public void add(String key, V value) { }

    // 移除指定元素
    public void remove(String key) { }

}
```

### 接口实现

#### 成员变量初始化

```java
/**
 * Trie 数据结构实现，用于存储字符串键和对应的值。
 *
 * @param <V> 值的类型参数
 * @author ZhangZiHeng
 * @date 2024-02-21
 */
public class Trie<V> {

    // 元素数量
    private int size;

    // 根节点信息
    private Node<V> root;
    
}
```

#### Node 节点初始化

```java
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
```

#### 元素数量

```java
    /**
     * 返回 Trie 中存储的键值对数量。
     *
     * @return Trie 中存储的键值对数量
     */
    public int size() {
        return size;
    }
```

#### 是否为空

```java
    /**
     * 检查 Trie 是否为空。
     *
     * @return 如果 Trie 不包含任何键值对，则返回 true，否则返回 false
     */
    public boolean isEmpty() {
        return size == 0;
    }
```

#### 清空 Trie

```java
    /**
     * 清空 Trie，移除所有的键值对。
     */
    public void clear() {
        size = 0;
        root = null;
    }
```

#### 是否包含给定的键

```java
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
```

#### 是否以给定前缀开头

```java
    /**
     * 检查 Trie 是否包含以给定前缀开头的任何键。
     *
     * @param prefix 要检查的前缀
     * @return 如果 Trie 包含以指定前缀开头的任何键，则返回 true，否则返回 false
     */
    public boolean startsWith(String prefix) {
        return node(prefix) != null;
    }
```

#### 添加指定的键值对

我们添加 `cat`、`dog`、`doggy`、`does`、`cast`、`add` 这六个单词，具体示例图如下：

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202402211649811.gif" alt="image" style="zoom:100%;float:left" />

**实现思路**

1. Step-1：如果是第一次添加，则需要将根节点进行初始化；
2. Step-2：遍历具体的 key ，并创建对应节点，将节点元素添加到根结点中；
3. Step-3：如果 Key 已经存在，如果存在则将旧值进行返回；
4. Step-4：如果 Key 不存在，则对节点重新赋值；

```java
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
```

#### 移除指定元素

移除指定元素，我们分为如下几种情况：

1. 找不到对应的 Key 或者 对应的 key 不是单词的结尾

   <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202402211727716.gif" alt="image" style="zoom:100%;float:left" />

2. 对应的 Key 存在子节点，例如我们删除 `dog`

   <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202402211733331.gif" alt="image" style="zoom:100%;float:left" />

3. 对应的 Key 不存在子节点 并且 对应的 Key 中存在好几个 Key，例如我们删除 `add`、`doggy`

   <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202402211748708.gif" alt="image" style="zoom:100%;float:left" />

**代码实现**

```java
    /**
     * 从 Trie 中移除指定的键及其关联的值。
     *
     * @param key 要移除的键
     * @return 如果键存在，则返回原值，否则返回 null
     */
    public V remove(String key) {
        /* Step-1: 找到最后一个字符 */
        Node<V> node = node(key);
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
```



### 单元测试

```java
public class TrieTest {

    @Test
    public void testAddAndGet() {
        Trie<Integer> trie = new Trie<>();
        trie.add("apple", 5);
        trie.add("banana", 10);

        Assertions.assertEquals(5, trie.get("apple"));
        Assertions.assertEquals(10, trie.get("banana"));
        Assertions.assertNull(trie.get("orange"));
    }

    @Test
    public void testContains() {
        Trie<Integer> trie = new Trie<>();
        trie.add("apple", 5);
        trie.add("banana", 10);

        Assertions.assertTrue(trie.contains("apple"));
        Assertions.assertTrue(trie.contains("banana"));
        Assertions.assertFalse(trie.contains("orange"));
    }

    @Test
    public void testRemove() {
        Trie<Integer> trie = new Trie<>();
        trie.add("apple", 5);
        trie.add("banana", 10);

        Assertions.assertEquals(5, trie.remove("apple"));
        Assertions.assertFalse(trie.contains("apple"));
        Assertions.assertNull(trie.get("apple"));
        Assertions.assertEquals(10, trie.get("banana"));
    }

    @Test
    public void testSizeAndClear() {
        Trie<Integer> trie = new Trie<>();
        trie.add("apple", 5);
        trie.add("banana", 10);

        Assertions.assertEquals(2, trie.size());
        Assertions.assertFalse(trie.isEmpty());

        trie.clear();

        Assertions.assertEquals(0, trie.size());
        Assertions.assertTrue(trie.isEmpty());
        Assertions.assertNull(trie.get("apple"));
        Assertions.assertNull(trie.get("banana"));
    }

    @Test
    public void testStartsWith() {
        Trie<Integer> trie = new Trie<>();
        trie.add("apple", 5);
        trie.add("banana", 10);

        Assertions.assertTrue(trie.startsWith("app"));
        Assertions.assertTrue(trie.startsWith("ban"));
        Assertions.assertFalse(trie.startsWith("ora"));
    }
}
```



## 总结

* 总体来说 Trie 这个数据结构实现还是比较容易，主要的难点还是在添加和删除节点元素。
* Trie 这个数据结构总体来说还是典型的使用空间来换取时间，整体的时间复杂度和对应 Key 的长度有关系。
