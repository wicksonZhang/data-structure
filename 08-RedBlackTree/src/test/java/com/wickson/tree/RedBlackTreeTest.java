package com.wickson.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeTest {

    @Test
    public void testAdd() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(15);
        assertEquals(3, tree.size());
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(15));
    }

    @Test
    public void testRemove() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.remove(5);
        assertEquals(2, tree.size());
        assertFalse(tree.contains(5));
    }

}
