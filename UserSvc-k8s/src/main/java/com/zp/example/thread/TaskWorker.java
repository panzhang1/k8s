/*
 * $Id$
 */
package com.zp.example.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class TaskWorker implements Runnable{
  private BlockingQueue tasks;
  private CountDownLatch latch; 
  
  public TaskWorker(BlockingQueue tasks,CountDownLatch latch){  
    this.tasks=tasks;   
    this.latch=latch;  
  } 
  
  @Override
  public void run() {
    String task = (String)tasks.poll();
    System.out.println("Worker "+ task + " do work begin.");  
    doWork();
    System.out.println("Worker "+ task + " do work complete.");  
    latch.countDown();
  }

  private void doWork(){  
    try {  
        Thread.sleep(8000);  
    } catch (InterruptedException e) {  
        e.printStackTrace();  
    }  
}
}
