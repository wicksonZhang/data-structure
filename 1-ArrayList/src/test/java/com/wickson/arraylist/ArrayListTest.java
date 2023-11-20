package com.wickson.arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    private ArrayList<Integer> arrayList;

    @BeforeEach
    public void setUp() {
        arrayList = new ArrayList<>();
    }

    @Test
    public void testInitialCapacity() {
        assertEquals(0, arrayList.size());
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void testAddAndSize() {
        arrayList.add(5);
        arrayList.add(10);
        arrayList.add(15);

        assertEquals(3, arrayList.size());
        assertFalse(arrayList.isEmpty());
    }

    @Test
    public void testGet() {
        arrayList.add(7);
        arrayList.add(14);

        assertEquals(7, arrayList.get(0));
        assertEquals(14, arrayList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(2));
    }

    @Test
    public void testSet() {
        arrayList.add(20);
        arrayList.add(30);

        assertEquals(20, arrayList.set(0, 25));
        assertEquals(25, arrayList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(2, 40));
    }

    @Test
    public void testAddAtIndex() {
        arrayList.add(100);
        arrayList.add(200);
        arrayList.add(1, 150);

        assertEquals(3, arrayList.size());
        assertEquals(150, arrayList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(5, 500));
    }

    @Test
    public void testRemove() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        assertEquals(2, arrayList.remove(1));
        assertEquals(2, arrayList.size());
        assertEquals(3, arrayList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(5));
    }

    @Test
    public void testIndexOf() {
        arrayList.add(11);
        arrayList.add(22);
        arrayList.add(33);

        assertEquals(1, arrayList.indexOf(22));
        assertEquals(-1, arrayList.indexOf(44));
    }

    @Test
    public void testClear() {
        arrayList.add(8);
        arrayList.add(16);

        assertFalse(arrayList.isEmpty());
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }
}
