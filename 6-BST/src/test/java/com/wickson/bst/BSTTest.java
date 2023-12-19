package com.wickson.bst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

/**
 * 二叉搜索树-单元测试
 */
public class BSTTest {

    private BST<Integer> bst;

    @BeforeEach
    public void setup() {
        bst = new BST<>();
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        bst.add(6);
        bst.add(8);
    }

    @Test
    public void testSize() {
        Assertions.assertEquals(7, bst.size());
        bst.remove(3);
        Assertions.assertEquals(6, bst.size());
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertFalse(bst.isEmpty());
        bst.clear();
        Assertions.assertTrue(bst.isEmpty());
    }

    @Test
    public void testAdd() {
        bst.add(9);
        Assertions.assertTrue(bst.contains(9));
    }

    @Test
    public void testRemove() {
        Assertions.assertTrue(bst.contains(3));
        bst.remove(3);
        Assertions.assertFalse(bst.contains(3));
    }

    @Test
    public void testContains() {
        Assertions.assertTrue(bst.contains(7));
        Assertions.assertFalse(bst.contains(10));
    }

    @Test
    public void testPreorderTraversal() {
        // Create a StringBuilder to capture the traversal output
        StringBuilder sb = new StringBuilder();
        System.setOut(new java.io.PrintStream(System.out) {
            @Override
            public void println(String x) {
                sb.append(x).append("\n");
            }
        });

        bst.preorderTraversal();
        // Check if the traversal output matches the expected order
        Assertions.assertEquals("node.element = 5\nnode.element = 3\nnode.element = 2\nnode.element = 4\nnode.element = 7\nnode.element = 6\nnode.element = 8\n", sb.toString());
    }

    @Test
    public void testCustomComparator() {
        // Comparator.reverseOrder()
        BST<String> stringBST = new BST<>(Comparator.reverseOrder());
        stringBST.add("apple");
        stringBST.add("banana");
        stringBST.add("cherry");

        // Test the traversal order based on the custom comparator
        StringBuilder sb = new StringBuilder();
        System.setOut(new java.io.PrintStream(System.out) {
            @Override
            public void println(String x) {
                sb.append(x).append("\n");
            }
        });

        stringBST.inorderTraversal();
        Assertions.assertEquals("node.element = cherry\nnode.element = banana\nnode.element = apple\n", sb.toString());
    }
}
