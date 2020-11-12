/*
 * $Id$
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
*/    
public class RemoveDuplicatedSortedLinkList {

  //other's
  public ListNode deleteDuplicates0(ListNode head) {
    ListNode t = new ListNode(0);
    t.next = head;
 
    ListNode p = t;
    while(p.next!=null&&p.next.next!=null){
        if(p.next.val == p.next.next.val){
            int dup = p.next.val;
            while(p.next!=null&&p.next.val==dup){
                p.next = p.next.next;
            }
        }else{
            p=p.next;
        }
 
    }
 
    return t.next;
  }
  
  //other's
  public ListNode deleteDuplicates2(ListNode head) {
    if(head == null || head.next == null)
        return head;

    ListNode prev = head;    
    ListNode p = head.next;

    while(p != null){
        if(p.val == prev.val){
            prev.next = p.next;
            p = p.next;
            //no change prev
        }else{
            prev = p;
            p = p.next; 
        }
    }

    return head;
  }
  
  //my
  public ListNode deleteDuplicates(ListNode head) {
    if(head == null || head.next == null)
      return head;
    
    ListNode start = new ListNode(-1);
    start.next = head;
    ListNode previous = start;
    
    ListNode temp = head;
    while(temp != null) {
      boolean hasDup = false;
      while(((temp.next != null) && (temp.val == temp.next.val))){
        temp = temp.next;
        hasDup = true;
      }
      
      if(hasDup) {
        previous.next = temp.next;
      }else{
        previous = temp;
      }
      temp = temp.next;
    }
    return start.next;
  }
  
  
  private ListNode getTestData2() {
    ListNode node5 = new ListNode(3);
    ListNode node4 = new ListNode(2);
    node4.next = node5;
    ListNode node3 = new ListNode(1);
    node3.next = node4;
    ListNode node2 = new ListNode(1);
    node2.next = node3;
    ListNode node1 = new ListNode(1);
    node1.next = node2;
    
    return node1;
  }
  
  private ListNode getTestData3() {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(1);
    node1.next = node2;
    
    return node1;
  }
  
  private ListNode getTestData() {
    ListNode node7 = new ListNode(5);
    ListNode node6 = new ListNode(4);
    node6.next = node7;
    ListNode node5 = new ListNode(4);
    node5.next = node6;
    ListNode node4 = new ListNode(3);
    node4.next = node5;
    ListNode node3 = new ListNode(3);
    node3.next = node4;
    ListNode node2 = new ListNode(2);
    node2.next = node3;
    ListNode node1 = new ListNode(1);
    node1.next = node2;
    
    return node1;
  }
  
  public static void main(String[] args) {
    RemoveDuplicatedSortedLinkList task = new RemoveDuplicatedSortedLinkList();

    ListNode data = task.getTestData();
    data.print();
    ListNode result = task.deleteDuplicates(data);
    result.print();
    //
    data = task.getTestData2();
    data.print();
    result = task.deleteDuplicates(data);
    result.print();
    //
    data = task.getTestData3();
    data.print();
    result = task.deleteDuplicates(data);
    result.print();
  }
}


