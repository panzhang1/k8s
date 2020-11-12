/*
 * $Id$
 */
package com.zp.example.algorithms;


public class LinkNode {
  int val;

  LinkNode next;

  LinkNode(int v) {
    val = v;
  }
  
  LinkNode(int v, LinkNode next) {
    val = v;
    next = next;
  }
}
