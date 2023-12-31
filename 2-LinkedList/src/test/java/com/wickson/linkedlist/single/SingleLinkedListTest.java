package com.wickson.linkedlist.single;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SingleLinkedListTest {

    private SingleLinkedList<String> list;

    @BeforeAll
    public void setUp() {
        list = new SingleLinkedList<>();
    }

    @Test
    public void testAddToEmptyList() {
        list.add(0, "A");
        assertEquals("A", list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    public void testAddToNonEmptyList() {
        list.add(0, "A");
        list.add(1, "B");
        list.add(1, "C");
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals("B", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    public void testGet() {
        list.add(0, "A");
        list.add(1, "B");
        list.add(2, "C");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    public void testSet() {
        list.add(0, "A");
        list.add(1, "B");
        list.set(1, "C");
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    public void testRemove() {
        list.add(0, "A");
        list.add(1, "B");
        list.add(2, "C");
        String removedElement = list.remove(1);
        assertEquals("B", removedElement);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    public void testIndexOf() {
        list.add(0, "A");
        list.add(1, "B");
        list.add(2, "C");
        list.add(3, null);
        assertEquals(1, list.indexOf("B"));
        assertEquals(3, list.indexOf(null));
    }

    @Test
    public void testClear() {
        list.add(0, "A");
        list.add(1, "B");
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testGetWithInvalidIndex() {
        list.get(0); // Expecting an exception since the list is empty
    }

    @Test
    public void testSetWithInvalidIndex() {
        list.set(0, "A"); // Expecting an exception since the list is empty
    }

    @Test
    public void testRemoveWithInvalidIndex() {
        list.remove(0); // Expecting an exception since the list is empty
    }

    @AfterAll
    public void printf() {
        System.out.println("toString() = " + list.toString());
    }

}