package com.wickson.bst.circle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CircleQueueTest {

    @Test
    public void testEmptyQueue() {
        CircleQueue<Integer> queue = new CircleQueue<>();
        
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        
        assertThrows(IndexOutOfBoundsException.class, queue::deQueue);
        assertThrows(IndexOutOfBoundsException.class, queue::front);
        System.out.println("queue = " + queue);
    }

    @Test
    public void testEnqueueAndDequeue() {
        CircleQueue<String> queue = new CircleQueue<>();

        queue.enQueue("One");
        queue.enQueue("Two");
        queue.enQueue("Three");

        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("One", queue.front());

        assertEquals("One", queue.deQueue());
        assertEquals("Two", queue.deQueue());
        assertEquals("Three", queue.deQueue());

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertThrows(IndexOutOfBoundsException.class, queue::deQueue);
        assertThrows(IndexOutOfBoundsException.class, queue::front);
        System.out.println("queue = " + queue);
    }

    @Test
    public void testQueueResize() {
        CircleQueue<Integer> queue = new CircleQueue<>();

        int initialCapacity = 20;
        for (int i = 0; i < initialCapacity; i++) {
            queue.enQueue(i);
        }
        System.out.println("queue = " + queue);
        assertEquals(initialCapacity, queue.size());

        for (int i = 0; i < initialCapacity; i++) {
            assertEquals(i, queue.deQueue());
        }

        System.out.println("queue = " + queue);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    // Add more test cases for boundary conditions, concurrent access, and exceptions if needed
}
