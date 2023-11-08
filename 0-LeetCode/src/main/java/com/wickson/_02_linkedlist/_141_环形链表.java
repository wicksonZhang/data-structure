package com.wickson._02_linkedlist;

/**
 * https://leetcode.cn/problems/linked-list-cycle/
 */
public class _141_环形链表 {

    public boolean hasCycle(ListNode head) {
        while (head != null && head.next != null) {
            ListNode slowNode = head.next;
            ListNode fastNode = head.next.next;
            if (fastNode == slowNode) {
                return true;
            }
        }
        return false;
    }

}
