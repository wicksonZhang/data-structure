package com.wickson.heap;

import com.wickson.printer.BinaryTrees;
import org.junit.Test;

public class BinaryHeapTest {

    @Test
    public void test() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(72);
        heap.add(68);
        heap.add(43);
        heap.add(43);
        heap.add(56);
        heap.add(98);
        heap.add(30);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(65);
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
    }

}