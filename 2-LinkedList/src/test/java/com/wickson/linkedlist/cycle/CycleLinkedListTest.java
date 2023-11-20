package com.wickson.linkedlist.cycle;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CycleLinkedListTest {

    @Test
    public void testAddAndGetElement() {
        CycleLinkedList<Integer> list = new CycleLinkedList<>();
        
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        
        list.add(0, 10);
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
        
        list.add(1, 20);
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(20), list.get(1));
    }

    @Test
    public void testRemoveElement() {
        CycleLinkedList<Character> list = new CycleLinkedList<>();
        
        list.add(0, 'a');
        list.add(1, 'b');
        list.add(2, 'c');
        
        assertEquals(Character.valueOf('b'), list.remove(1));
        assertEquals(2, list.size());
        assertEquals(Character.valueOf('c'), list.get(1));
    }
}
