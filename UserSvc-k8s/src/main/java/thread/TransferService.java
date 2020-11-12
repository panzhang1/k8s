/*
 * $Id$
 */
package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransferService {
  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(10);
    Account from = new Account(1000);
    Account to = new Account(1000);
    for(int i=0; i<50;i++){
      service.execute(new Transfer(from, to , 10));
      service.execute(new Transfer(to, from , 10));
    }
    service.shutdown();
    try {
      service.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    System.out.println("from Account balance:" + from.getBalance());
    System.out.println("to Account balance:" + to.getBalance());
  }
}

class Transfer implements Runnable{
  private Account from;
  private Account to;
  private int amount;
  
  public Transfer(Account from, Account to, int amount){
    this.from = from;
    this.to = to;
    this.amount = amount;
  }
  
  //this method should be transactional
  public void doTransfer(Account from, Account to, int amount){
    from.debit(amount);
    to.credit(amount);
  }
  
  @Override
  public void run() {
    try {
      TimeUnit.MILLISECONDS.sleep(50);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    doTransfer(from, to, amount);
  }
}
