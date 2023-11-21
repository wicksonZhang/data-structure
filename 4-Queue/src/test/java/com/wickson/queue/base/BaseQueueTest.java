package com.wickson.queue.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BaseQueueTest {

    private BaseQueue<Integer> queue;

    @BeforeEach
    public void setup() {
        queue = new BaseQueue<>();
    }

    @Test
    public void testEnqueueAndSize() {
        queue.enQueue(5);
        queue.enQueue(10);
        Assertions.assertEquals(2, queue.size());
    }

    @Test
    public void testDequeueAndFront() {
        queue.enQueue(5);
        queue.enQueue(10);
        Assertions.assertEquals(5, queue.front());
        Assertions.assertEquals(5, queue.deQueue());
        Assertions.assertEquals(1, queue.size());
        Assertions.assertEquals(10, queue.front());
    }

    @Test
    public void testIsEmptyAndClear() {
        Assertions.assertTrue(queue.isEmpty());
        queue.enQueue(5);
        queue.enQueue(10);
        Assertions.assertFalse(queue.isEmpty());
        queue.clear();
        Assertions.assertTrue(queue.isEmpty());
    }
    
}
