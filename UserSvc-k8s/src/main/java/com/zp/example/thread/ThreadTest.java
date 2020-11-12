package com.zp.example.thread;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/*
 * $Id$
 */

public class ThreadTest {

  public static void main(String[] args) {
    ThreadTest test = new ThreadTest();
    try {
      test.test1();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public void test2(){
    try {
      List<BigInteger> result = aSecondOfPrimes();
      System.out.println("result:" + result);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void test1() throws InterruptedException{
    BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<BigInteger>(5);
    PrimeProducer producer = new PrimeProducer(queue);
    new Thread(producer).start();
    try {
      TimeUnit.SECONDS.sleep(5);
    } finally {
      producer.cancel();
    }
  }
  
  public List<BigInteger> aSecondOfPrimes() throws InterruptedException {
    PrimeGenerator generator = new PrimeGenerator();
    new Thread(generator).start();
    try {
      TimeUnit.SECONDS.sleep(1);
    } finally {
    generator.cancel();
    }
    return generator.get();
    }
}

class PrimeGenerator implements Runnable {
  private final List<BigInteger> primes = new ArrayList<BigInteger>();

  private volatile boolean cancelled;

  public void run() {
    BigInteger p = BigInteger.ONE;
    while (!cancelled) {
      p = p.nextProbablePrime();
      synchronized (this) {
        primes.add(p);
      }
    }
  }

  public void cancel() {
    cancelled = true;
  }

  public synchronized List<BigInteger> get() {
    return new ArrayList<BigInteger>(primes);
  }
}


class PrimeProducer implements Runnable {
  private final BlockingQueue<BigInteger> queue;

  PrimeProducer(BlockingQueue<BigInteger> queue) {
    this.queue = queue;
  }

  public void run() {
    try {
      BigInteger p = BigInteger.ONE;
      while (!Thread.currentThread().isInterrupted()) {
        p = p.nextProbablePrime();
        queue.put(p);
        System.out.println(p.toString());
      }
    } catch (InterruptedException consumed) {
      /* Allow com.zp.example.thread to exit */
      System.out.println("InterruptedException");
    } finally {
      System.out.println("end here");
    }

    System.out.println("end here 2@");
  }

  public void cancel() {
    System.out.println("cancel");
    Thread.currentThread().interrupt();
  }
}
