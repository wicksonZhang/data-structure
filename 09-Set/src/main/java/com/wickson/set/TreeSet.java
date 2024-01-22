package com.wickson.set;

import com.wickson.tree.RedBlackTree;

/**
 * 红黑树实现集合
 */
public class TreeSet<E> implements Set<E> {

    private final RedBlackTree<E> tree = new RedBlackTree<E>();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inorderTraversal(new Visitor<E>(){
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
