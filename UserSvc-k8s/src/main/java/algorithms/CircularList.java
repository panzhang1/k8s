/*
 * $Id$
 */
package algorithms;

public class CircularList 
{ 
  static class Node 
    { int val; Node next; 
      Node(int v) { val = v; } 
    } 
  Node next(Node x) 
    { return x.next; } 
  int val(Node x) 
    { return x.val; } 
  Node insert(Node x, int v) 
    { Node t = new Node(v); 
      if (x == null) t.next = t; 
      else { t.next = x.next; x.next = t; } 
      return t; 
    } 
  void remove(Node x) 
    { x.next = x.next.next; } 
} 

