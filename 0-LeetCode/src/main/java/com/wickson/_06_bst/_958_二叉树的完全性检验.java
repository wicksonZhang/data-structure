package com.wickson._06_bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
 */
public class _958_二叉树的完全性检验 {

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        boolean leaf = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.offer(poll.left);
            } else if (poll.right != null) { // poll.left == null && poll.right != null
                return false;
            }

            if (poll.right != null) {
                queue.offer(poll.right);
            } else { // poll.right == true
                leaf = true;
            }
        }
        return leaf;
    }

}
