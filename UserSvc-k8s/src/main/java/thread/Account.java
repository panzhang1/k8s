/*
 * $Id$
 */
package thread;

import java.util.concurrent.TimeUnit;

public class Account {
  private int balance;
  
  public Account(int balance){
    this.balance = balance;
  }
  
  public synchronized void debit(int amount){
    balance -= amount;
  }
  
  public synchronized void credit(int amount){
    balance += amount;
  }

  public synchronized int getBalance() {
    return balance;
  }

  public synchronized void setBalance(int balance) {
    this.balance = balance;
  }
}
