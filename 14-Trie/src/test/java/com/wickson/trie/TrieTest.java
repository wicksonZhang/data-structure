package com.wickson.trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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