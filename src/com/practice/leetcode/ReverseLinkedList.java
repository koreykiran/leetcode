package com.practice.leetcode;

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
             ListNode prev = null;
             ListNode next = null;
             while(head!=null){
                 next = head.next;
                 head.next= prev;
                 prev = head;
                 head=next;
             }
             return prev;
         }

        public ListNode reverseListRec(ListNode head) {

            if(head==null || head.next==null)
                return head;
            ListNode prev = reverseListRec(head.next);
            head.next.next=head;
            head.next = null;
            return prev;
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
