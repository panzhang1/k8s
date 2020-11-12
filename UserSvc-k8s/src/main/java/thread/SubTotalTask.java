/*
 * $Id$
 */
package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class SubTotalTask extends RecursiveTask<Integer>{

  private static final long serialVersionUID = -2266483212762826664L;
  private static final int THREADHOLD = 2;
  private int start,end;
  
  public SubTotalTask(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  protected Integer compute() {
    int sum = 0;
    boolean canCompute = (end - start) <= THREADHOLD;
    if (canCompute) {
      for(int i = start; i <= end; i++) {
        sum += i;
      }
    } else {
      int middle = (start + end) / 2;
      SubTotalTask left = new SubTotalTask(start, middle);
      SubTotalTask right = new SubTotalTask(middle + 1, end);
      //execute
      left.fork();
      right.fork();
      //get result
      sum = left.join() + right.join();
    }
    return sum;
  }

  private static Integer compuateUseFork(int end) {
    ForkJoinPool pool = new ForkJoinPool();
    SubTotalTask task = new SubTotalTask(1,end);
    Future<Integer> result = pool.submit(task);
    Integer total = 0;
    try {
      total = result.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } finally {
      
    }
    
    return total;
  }
  
  
  public static void main(String[] args) {
    long start = System.nanoTime();
    int result1 = compuateUseFork(100000);
    long end = System.nanoTime();
    System.out.println("result1:" + (end-start));
  }
}
