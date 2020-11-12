/*
 * $Id$
 */
package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchManager {
  private static int WORKER_POOL_SIZE = 5;
  private static int taskCount = 8;
  
  public static void main(String[] args){ 
    CountDownLatchManager manager = new CountDownLatchManager();
    manager.execute();
  }
  
  public void execute(){
    //put all task's to BlockingQueue
    BlockingQueue tasks = getTasks();
    //then use thread pool to fetch it
    ExecutorService exec = Executors.newFixedThreadPool(WORKER_POOL_SIZE);
    
    CountDownLatch latch = new CountDownLatch(taskCount);//
    for(int i = 0; i < taskCount; i++) {
      TaskWorker worker = new TaskWorker(tasks, latch);  
      exec.execute(new Thread(worker));
    }
    exec.shutdown();
    
    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }//
    System.out.println("all work done.");  
  }
  
  private BlockingQueue getTasks(){
    BlockingQueue queue = new ArrayBlockingQueue(taskCount);
    for(int i = 0; i < taskCount; i++){
      try {
        queue.put(i + "");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return queue;
  }
}
