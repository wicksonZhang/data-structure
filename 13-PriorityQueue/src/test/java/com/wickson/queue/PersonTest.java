package com.wickson.queue;

import org.junit.Test;

/**
 * @author ZhangZiHeng
 * @date 2024-02-06
 */
public class PersonTest {

    @Test
    public void test() {
        PriorityQueue<Object> queue = new PriorityQueue<>();
        queue.enQueue(new Person("jack" , 1));
        queue.enQueue(new Person("tom" , 2));
        queue.enQueue(new Person("rose" , 3));
        queue.enQueue(new Person("tim" , 4));
        queue.enQueue(new Person("jerry" , 5));
        queue.enQueue(new Person("Jim" , 6));
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

}
