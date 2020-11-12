/*
 * $Id$
 */
package algorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeManager {
  private Node root;

  public void createTestTree() {
    /*
                    E
            D                 H
      B                 F
    A   C                   G
    */
    
    Node a = new Node(new Item("A"), null, null);
    Node c = new Node(new Item("C"), null, null);
    Node b = new Node(new Item("B"), a, c);
    Node d = new Node(new Item("D"), b, null);

    Node g = new Node(new Item("G"), null, null);
    Node f = new Node(new Item("F"), null, g);
    Node h = new Node(new Item("H"), f, null);

    Node e = new Node(new Item("E"), d, h);

    root = e;

  }

  /**
   * preorder
   * 
   * @param h
   */
  private void traverseR(Node h) {
    if (h == null)
      return;
    h.item.visit();
    traverseR(h.l);
    traverseR(h.r);
  }

  /**
   * Recusive traverse
   */
  void traverseR() {
    System.out.println("traverse using Recusive");
    traverseR(root);
  }

  private void traverseS(Node h) {
    Stack<Node> s = new Stack<Node>();
    s.push(h);
    while (!s.empty()) {
      h = s.pop();
      h.item.visit();
      if (h.r != null)
        s.push(h.r);
      if (h.l != null)
        s.push(h.l);
    }
  }

  void traverseS() {
    System.out.println("traverse using Stack");
    traverseS(root);
  }

  
  private void traverseQ(Node h) 
  { Queue<Node> q = new LinkedList<Node>(); 
    q.offer(h); 
    while (!q.isEmpty()) 
      { 
        h = q.poll(); 
        h.item.visit(); 
        if (h.l != null) q.offer(h.l); 
        if (h.r != null) q.offer(h.r); 
      } 
  } 
  
  /**
   * Traverse by Level
   */
void traverseQ() 
  { 
  System.out.println("traverse by level");
  traverseQ(root); 
  } 

  public static void main(String[] args) {
    TreeManager tree = new TreeManager();
    tree.createTestTree();
    tree.traverseR();
    tree.traverseS();
    tree.traverseQ();
  }
}

class Item {
  String val;

  public Item(String value) {
    this.val = value;
  }

  public void visit() {
    System.out.println(val);
  }
}

class Node {
  Item item;

  Node l;

  Node r;

  Node(Item v, Node l, Node r) {
    this.item = v;
    this.l = l;
    this.r = r;
  }
}
