package com.wickson.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    public void testEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testPushAndPop() {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.isEmpty());

        stack.push("First");
        stack.push("Second");
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());

        assertEquals("Second", stack.pop());
        assertEquals("First", stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testTop() {
        Stack<Double> stack = new Stack<>();
        stack.push(3.14);
        assertEquals(3.14, stack.top());
        assertEquals(1, stack.size());
    }

    @Test
    public void testPopOnEmptyStack() {
        Stack<Character> stack = new Stack<>();
        assertThrows(IndexOutOfBoundsException.class, stack::pop);
    }

    @Test
    public void testTopOnEmptyStack() {
        Stack<Boolean> stack = new Stack<>();
        assertThrows(IndexOutOfBoundsException.class, stack::top);
    }

}
