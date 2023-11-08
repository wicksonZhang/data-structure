package com.wickson._02_linkedlist;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class _206_反转链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);

        System.out.println("Original Linked List:");
        printLinkedList(head);

        ListNode reversedHead = reverseList2(head);

        System.out.println("Reversed Linked List:");
        printLinkedList(reversedHead);
    }

    /**
     * 需求：反转链表
     * <p>
     * list：   1 -> 2 -> 3 -> 4 -> 5 -> null
     * reverse：5 -> 4 -> 3 -> 2 -> 1 -> null
     *
     * @param head 头节点
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        // 递归反分为两部分：第一部分找到程序出口，第二部分处理核心逻辑
        // ================================= 寻找出口 =================================
        // 1. 为什么 reverseList(head) 是从 head.next 开始，而不是从 head 开始？
        // >  如果从 head 开始我们最后的结果就是死循环。
        // >  解决方案： head.next。
        // >  递归的核心思想是反转剩余部分的链表，然后再处理当前节点。
        // ================================= 寻找出口 =================================
        // Step-1: 寻找出口
        if (head == null || head.next == null) {
            return head;
        }
        System.out.println("head = " + head);
        ListNode listNode = reverseList1(head.next);
        // ================================= 逻辑处理 =================================
        // 1. listNode 返回的又是谁？head 最后指向的是谁？
        // >  listNode -> ListNode{val=5, next=null};
        // >  head -> ListNode{val=4, next=ListNode{val=5, next=null}}.
        // ================================= 逻辑处理 =================================
        head.next.next = head;
        head.next = null;
        return listNode;
    }

    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tempNode = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = tempNode;
            tempNode = head;
            head = nextNode;
        }
        return tempNode;
    }


//    public static ListNode reverseList(ListNode head) {
//        // 基本情况：如果链表为空或只有一个节点，直接返回它
//        if (head == null || head.next == null) {
//            return head;
//        }
//        // 递归调用以反转剩余的链表
//        ListNode reversedList = reverseList(head.next);
//        // 当前节点的下一个节点的指针指向当前节点，实现反转
//        head.next.next = head;
//        head.next = null;
//
//        return reversedList; // 返回新的头节点
//    }

    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

}

