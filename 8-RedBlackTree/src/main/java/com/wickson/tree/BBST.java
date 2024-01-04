package com.wickson.tree;

import java.util.Comparator;

public class BBST<E> extends BST<E> {

    public BBST() {
        this(null);
    }

    public BBST(Comparator<? super E> comparator) {
        super(comparator);
    }

    /**
     * 左旋转
     *
     * @param grand 节点
     */
    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.rightNode;
        Node<E> child = parent.leftNode;
        grand.rightNode = child;
        parent.leftNode = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 右旋转
     *
     * @param grand 节点
     */
    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.rightNode;
        Node<E> child = grand.leftNode;
        grand.rightNode = child;
        grand.leftNode = grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 让 parent 成为子树的根节点
        parent.parentNode = grand.parentNode;
        if (grand.isLeftChild()) {
            grand.parentNode.leftNode = parent;
        } else if (grand.isLeftChild()) {
            grand.parentNode.leftNode = parent;
        } else {
            root = parent;
        }

        // 更新 child 的parent
        if (child != null) {
            child.parentNode = grand;
        }

        // 更新 grand 的 parent
        grand.parentNode = parent;
    }

}
