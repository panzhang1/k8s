package com.zp.example.leetcode;


/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/
public class AddTwoNumbers {
  
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(-1);
    ListNode previous = head;
    ListNode left = l1;
    ListNode right = l2;
    boolean increase = false;
    while(left != null || right != null) {
      int result = 0;
      if (left == null) {
        result = right.val;
      } else if (right == null) {
        result = left.val;
      } else {
        result = left.val + right.val;
      }
      if (increase) {
        result ++;
      }
      
      if (result > 9) {
        increase = true;
        result = result - 10;
      } else {
        increase = false;
      }
      
      ListNode node = new ListNode(result);
      previous.next = node;
      
      previous = node;
      
      if (left != null) {
        left = left.next;
      }
      
      
      if (right != null) {
        right = right.next;
      }
    }
    
    if (increase) {
      ListNode node = new ListNode(1);
      previous.next = node;
    }
    
    return head.next;
  }
  
  private ListNode getFirstNode() {
    ListNode node3 = new ListNode(3);
    ListNode node2 = new ListNode(4);
    node2.next = node3;
    ListNode node1 = new ListNode(2);
    node1.next = node2;
    
    return node1;
  }
  
  private ListNode getSecondNode() {
    ListNode node3 = new ListNode(4);
    ListNode node2 = new ListNode(6);
    node2.next = node3;
    ListNode node1 = new ListNode(5);
    node1.next = node2;
    
    return node1;
  }
  
  public static void main(String[] args) {
    AddTwoNumbers task = new AddTwoNumbers();
    
    ListNode result = task.addTwoNumbers(task.getFirstNode(), task.getSecondNode());
    result.print();
  }
}