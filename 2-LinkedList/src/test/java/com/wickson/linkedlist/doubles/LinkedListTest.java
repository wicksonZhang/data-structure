package com.wickson.linkedlist.doubles;


import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LinkedListTest {

    @Test
    public void testAddAndGet() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0, 1);
        linkedList.add(1, 2);
        linkedList.add(2, 3);

        assertEquals((Object) 1, linkedList.get(0));
        assertEquals((Object) 2, linkedList.get(1));
        assertEquals((Object) 3, linkedList.get(2));
        System.out.println("linkedList ===> " + linkedList);
    }

    @Test
    public void testSet() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(0, "apple");
        linkedList.add(1, "banana");

        assertEquals("apple", linkedList.set(0, "orange"));
        assertEquals("orange", linkedList.get(0));
        System.out.println("linkedList ===> " + linkedList);
    }

    @Test
    public void testRemove() {
        LinkedList<Character> linkedList = new LinkedList<>();
        linkedList.add(0, 'a');
        linkedList.add(1, 'b');
        linkedList.add(2, 'c');

        assertEquals('b', (char) linkedList.remove(1));
        assertEquals("{ size = 2 , Node = [ a,c ]}", linkedList.toString());
    }

    @Test
    public void testIndexOf() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0, 10);
        linkedList.add(1, 20);
        linkedList.add(2, 30);

        assertEquals(1, linkedList.indexOf(20));
        assertEquals(-1, linkedList.indexOf(40));
    }

    @Test
    public void testClear() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(0, "one");
        linkedList.add(1, "two");

        linkedList.clear();
        assertEquals("{ size = 0 , Node = [  ]}", linkedList.toString());
    }
}