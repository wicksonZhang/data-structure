package com.wickson._02_linkedlist;

public class _206_反转链表 {
    public static ListNode reverseList(ListNode head) {
        // 基本情况：如果链表为空或只有一个节点，直接返回它
        if (head == null || head.next == null) {
            return head;
        }

        // 递归调用以反转剩余的链表
        ListNode reversedList = reverseList(head.next);

        // 当前节点的下一个节点的指针指向当前节点，实现反转
        head.next.next = head;
        head.next = null;

        return reversedList; // 返回新的头节点
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original Linked List:");
        printLinkedList(head);

        ListNode reversedHead = reverseList(head);

        System.out.println("Reversed Linked List:");
        printLinkedList(reversedHead);
    }

    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

}

