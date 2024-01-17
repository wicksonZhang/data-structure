package com.wickson.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeMapTest {

    private TreeMap<Integer, String> treeMap;

    @BeforeEach
    public void setUp() {
        // 在每个测试方法执行之前初始化 TreeMap
        treeMap = new TreeMap<>();
        treeMap.put(5, "five");
        treeMap.put(3, "three");
        treeMap.put(8, "eight");
        treeMap.put(2, "two");
        treeMap.put(4, "four");
    }

    @Test
    public void testSize() {
        Assertions.assertEquals(5, treeMap.size());
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertFalse(treeMap.isEmpty());
    }

    @Test
    public void testClear() {
        treeMap.clear();
        Assertions.assertEquals(0, treeMap.size());
        Assertions.assertTrue(treeMap.isEmpty());
    }

    @Test
    public void testPut() {
        // 添加新元素
        Assertions.assertNull(treeMap.put(1, "one"));
        Assertions.assertEquals(6, treeMap.size());

        // 更新已存在的元素
        Assertions.assertEquals("one", treeMap.put(1, "newOne"));
        Assertions.assertEquals(6, treeMap.size());
    }

    @Test
    public void testGet() {
        Assertions.assertEquals("five", treeMap.get(5));
        Assertions.assertEquals("three", treeMap.get(3));
        Assertions.assertNull(treeMap.get(10)); // 不存在的键返回 null
    }

    @Test
    public void testRemove() {
        Assertions.assertEquals("five", treeMap.remove(5));
        Assertions.assertNull(treeMap.get(5)); // 已删除的键应返回 null
        Assertions.assertEquals(4, treeMap.size());
    }

    @Test
    public void testContainKey() {
        Assertions.assertTrue(treeMap.containKey(3));
        Assertions.assertFalse(treeMap.containKey(10));
    }

    @Test
    public void testContainValue() {
        Assertions.assertTrue(treeMap.containValue("eight"));
        Assertions.assertFalse(treeMap.containValue("ten"));
    }

    @Test
    public void testTraversal() {
        // 创建 Visitor 接口的实现类，用于测试遍历
        class TestVisitor extends Map.Visitor<Integer, String> {
            final StringBuilder result = new StringBuilder();
            @Override
            public boolean visit(Integer key, String value) {
                result.append(key).append(":").append(value).append(" ");
                return false; // 返回 false，以便遍历所有节点
            }
        }

        TestVisitor visitor = new TestVisitor();
        treeMap.traversal(visitor);

        Assertions.assertEquals("2:two 3:three 4:four 5:five 8:eight ", visitor.result.toString());
    }
}