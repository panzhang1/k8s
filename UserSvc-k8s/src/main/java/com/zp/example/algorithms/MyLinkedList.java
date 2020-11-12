/*
 * $Id$
 */
package com.zp.example.algorithms;

public class MyLinkedList {

  public LinkNode reverse(LinkNode x) {
    LinkNode t, y = x.next, r = null;
    while (y != null) {
      t = y.next;
      y.next = r;
      r = y;
      y = t;
    }
    x.next = r;
    return x;
  }

  public void traverse(LinkNode x) {
    LinkNode t = x.next;
    while (t != null) {
      visit(t.val);
      t = t.next;
    }

    /*
     * for (Node t = x; t != null; t = t.next) { visit(t.val); }
     */
  }

  //insert sort
  public LinkNode sort(LinkNode a) {
    LinkNode t, u, x, b = new LinkNode(0, null);
    // set first value a
    while (a.next != null) {
      t = a.next;
      u = t.next;
      a.next = u;
      for (x = b; x.next != null; x = x.next)
        if (x.next.val > t.val)
          break;
      t.next = x.next;
      x.next = t;
    }
    return b;
  }

  public void visit(int i) {
    System.out.println(i);
  }

  public LinkNode init(int N) {
    //first node is a empty node
    LinkNode header = new LinkNode(-1, null);
    LinkNode x = header;
    LinkNode b;
    for (int i = 1; i <= N; i++) {
      b = new LinkNode(i);
      x.next = b;
      x = b;
    }

    return header;
  }

  public static void main(String[] args) {
    MyLinkedList my = new MyLinkedList();
    LinkNode header = my.init(20);
    System.out.println("traverse original data.");
    my.traverse(header);
    System.out.println("reverse.");
    LinkNode tail = my.reverse(header);
    System.out.println("traverse the reversed data.");
    my.traverse(tail);
    System.out.println("Sort.");
    LinkNode sort = my.sort(tail);
    System.out.println("sort the data.");
    my.traverse(sort);
  }
}
