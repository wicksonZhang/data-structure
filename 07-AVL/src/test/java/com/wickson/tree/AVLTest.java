package com.wickson.tree;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTest {

    private AVL<Integer> avlTree;

    @BeforeEach
    void setUp() {
        avlTree = new AVL<>();
        // 添加一些元素作为测试数据
        avlTree.add(5);
        avlTree.add(3);
        avlTree.add(7);
        avlTree.add(2);
        avlTree.add(4);
        avlTree.add(6);
        avlTree.add(8);
    }

    @Test
    void testSizeAndClear() {
        assertEquals(7, avlTree.size());
        assertFalse(avlTree.isEmpty());

        avlTree.clear();

        assertEquals(0, avlTree.size());
        assertTrue(avlTree.isEmpty());
    }

    @Test
    void testBalanceAfterAdd() {
        // 添加节点后，检查 AVL 树是否保持平衡
        assertTrue(isBalanced(avlTree.root));
    }

    @Test
    void testBalanceAfterRemove() {
        // 在移除节点后，检查 AVL 树是否保持平衡
        avlTree.remove(3);
        assertTrue(isBalanced(avlTree.root));
    }

    private boolean isBalanced(BinaryTree.Node<Integer> node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = ((AVL.AVLNode<Integer>) node).balanceFactor();
        return Math.abs(balanceFactor) <= 1 && isBalanced(node.leftNode) && isBalanced(node.rightNode);
    }
}
