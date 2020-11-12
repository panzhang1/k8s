/*
 * $Id$
 */
package com.zp.example.algorithms;

public class ArrayStack {
  private int[] s;

  private int N;

  ArrayStack(int maxN) {
    s = new int[maxN];
    N = 0;
  }

  boolean isEmpty() {
    return (N == 0);
  }

  void push(int item) {
    s[N++] = item;
  }

  int pop() {
    return s[--N];
  }
}
