package com.wickson._06_bst;

/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class _104_二叉树的最大深度 {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
