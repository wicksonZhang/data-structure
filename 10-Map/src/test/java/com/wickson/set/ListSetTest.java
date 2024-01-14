package com.wickson.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListSetTest {

    private ListSet<Integer> set;

    @BeforeEach
    public void setUp() {
        set = new ListSet<>();
    }

    @Test
    public void testSize() {
        Assertions.assertEquals(0, set.size());

        set.add(1);
        Assertions.assertEquals(1, set.size());

        set.add(2);
        set.add(3);
        Assertions.assertEquals(3, set.size());

        set.remove(2);
        Assertions.assertEquals(2, set.size());

        set.clear();
        Assertions.assertEquals(0, set.size());
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(set.isEmpty());

        set.add(1);
        Assertions.assertFalse(set.isEmpty());

        set.remove(1);
        Assertions.assertTrue(set.isEmpty());
    }

    @Test
    public void testClear() {
        set.add(1);
        set.add(2);
        set.add(3);

        Assertions.assertFalse(set.isEmpty());

        set.clear();

        Assertions.assertTrue(set.isEmpty());
        Assertions.assertEquals(0, set.size());
    }

    @Test
    public void testContains() {
        set.add(1);
        set.add(2);

        Assertions.assertTrue(set.contains(1));
        Assertions.assertTrue(set.contains(2));
        Assertions.assertFalse(set.contains(3));
    }

    @Test
    public void testAdd() {
        set.add(1);
        set.add(2);

        Assertions.assertEquals(2, set.size());
        Assertions.assertTrue(set.contains(1));
        Assertions.assertTrue(set.contains(2));
    }

    @Test
    public void testRemove() {
        set.add(1);
        set.add(2);
        set.remove(1);
        Assertions.assertEquals(1, set.size());
        Assertions.assertFalse(set.contains(1));
        Assertions.assertTrue(set.contains(2));
    }

    @Test
    public void testTraversal() {
        set.add(1);
        set.add(2);
        set.add(3);

        StringBuilder result = new StringBuilder();
        Visitor<Integer> visitor = new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                result.append(element).append(" ");
                return false;
            }
        };
        set.traversal(visitor);
        Assertions.assertEquals("1 2 3 ", result.toString());
    }

}
