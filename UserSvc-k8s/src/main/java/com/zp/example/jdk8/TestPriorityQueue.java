/*
 * $Id$
 */
package com.zp.example.jdk8;

import java.util.PriorityQueue;

public class TestPriorityQueue {

  public static void main(String[] args) {
    TestPriorityQueue test = new TestPriorityQueue();
    test.testQueue();

  }
  
  public void testQueue() {
    PriorityQueue<String> queue = new PriorityQueue<>();
    queue.offer("2");
    queue.offer("3");
    queue.offer("4");
    queue.offer("5");
    queue.offer("6");
    queue.offer("7");
    queue.offer("8");
    queue.offer("1");
    
    System.out.println(queue);
  }

}
