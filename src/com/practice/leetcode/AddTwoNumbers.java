package com.practice.leetcode;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int c = 0;
        ListNode sent = new ListNode(-1);
        ListNode resHead = sent;
        int sum = 0, x, y;
        while (l1 != null || l2 != null) {
            x = l1 != null ? l1.val : 0;
            y = l2 != null ? l2.val : 0;
            sum = x + y + c;
            if (sum > 9) {
                c = 1;
            } else {
                c = 0;
            }
            sent.next = new ListNode(sum % 10);
            sent = sent.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (c == 1) {
            sent.next = new ListNode(1);
        }


        return resHead.next;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

