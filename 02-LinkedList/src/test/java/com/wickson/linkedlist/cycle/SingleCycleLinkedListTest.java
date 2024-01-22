package com.wickson.linkedlist.cycle;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingleCycleLinkedListTest {

    @Test
    public void testAddRemoveElements() {
        SingleCycleLinkedList<Integer> list = new SingleCycleLinkedList<>();

        // Add elements
        list.add(0, 10);
        list.add(1, 20);
        list.add(2, 30);

        // Remove elements
        assertEquals(Integer.valueOf(20), list.remove(1));
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(30), list.get(1));

        assertEquals(Integer.valueOf(10), list.remove(0));
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(30), list.get(0));
    }

    @Test
    public void testRemoveElement() {
        SingleCycleLinkedList<Character> list = new SingleCycleLinkedList<>();

        list.add(0, 'a');
        list.add(1, 'b');
        list.add(2, 'c');

        assertEquals('b', list.remove(1));
        assertEquals(3, list.size());
        assertEquals('c', list.get(1));
    }

}