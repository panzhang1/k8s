/*
 * $Id$
 */
package com.zp.example.algorithms.graph;

public class Node {
  int v;

  Node next;

  Node(int x, Node t) {
    v = x;
    next = t;
  }
}
