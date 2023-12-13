package com.wickson._06_bst;

/**
 * https://leetcode.cn/problems/invert-binary-tree/description/
 */
public class _226_翻转二叉树 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode treeNode = root.right;
        root.right = root.left;
        root.left = treeNode;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

}
