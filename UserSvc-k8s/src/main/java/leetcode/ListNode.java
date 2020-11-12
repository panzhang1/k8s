/*
 * $Id$
 */
package leetcode;

public class ListNode {
  int val;
  ListNode next;
  
  public ListNode(int x) { 
    this.val = x; 
  }
  
  public void print() {
    StringBuilder sb = new StringBuilder();
    ListNode temp = this;
    while (temp != null) {
      sb.append(temp.val + "->");
      temp = temp.next;
    }
    System.out.println(sb.toString());
  }
}
