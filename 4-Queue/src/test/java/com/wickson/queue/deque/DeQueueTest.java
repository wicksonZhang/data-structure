package com.wickson.queue.deque;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeQueueTest {

    @Test
    public void testEmptyQueue() {
        DeQueue<Integer> queue = new DeQueue<>();
        
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        // Ensure dequeue operations on an empty queue throw exceptions or return expected values
        assertThrows(IndexOutOfBoundsException.class, queue::deQueueFront);
        assertThrows(IndexOutOfBoundsException.class, queue::deQueueRear);
    }

    @Test
    public void testEnqueueDequeue() {
        DeQueue<String> queue = new DeQueue<>();

        queue.enQueueRear("First");
        queue.enQueueFront("New First");

        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("New First", queue.deQueueFront());
        assertEquals("First", queue.deQueueRear());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testFrontRear() {
        DeQueue<Character> queue = new DeQueue<>();

        queue.enQueueRear('A');
        queue.enQueueRear('B');
        queue.enQueueFront('C');
        queue.enQueueFront('D');

        assertEquals('D', queue.front());
        assertEquals('B', queue.rear());
    }

    @Test
    public void testBoundaryConditions() {
        DeQueue<Integer> queue = new DeQueue<>();

        // Single element queue
        queue.enQueueRear(42);
        assertEquals(42, (int) queue.deQueueFront());
        assertTrue(queue.isEmpty());

        // Enqueue a large number of elements and dequeue them one by one
        for (int i = 0; i < 1000; i++) {
            queue.enQueueRear(i);
        }

        for (int i = 0; i < 1000; i++) {
            assertEquals(i, (int) queue.deQueueFront());
        }
        assertTrue(queue.isEmpty());
    }
}
