/*
 * $Id$
 */
package designpattern;

public class StrategyPattern {

  public static void main(String[] args) {
    HardPolice hp = new HardPolice();
    NicePolice ep = new NicePolice();

    // In situation 1, a hard officer is met
            // In situation 2, a nice officer is met
    Situation s1 = new Situation(hp);
    Situation s2 = new Situation(ep);

    //the result based on the kind of police officer.
    s1.handleByPolice(10);
    s2.handleByPolice(10);  

  }

}

interface Strategy {
  //defind a method for police to process speeding case.
  public void processSpeeding(int speed);
}

class NicePolice implements Strategy{
  @Override
  public void processSpeeding(int speed) {
      System.out.println("This is your first time, be sure don't do it again!");      
  }
}

class HardPolice implements Strategy{
  @Override
  public void processSpeeding(int speed) {
      System.out.println("Your speed is "+ speed+ ", and should get a ticket!");
  }
}

class Situation {
  private Strategy strategy;

  public Situation(Strategy strategy){
      this.strategy = strategy;
  }

  public void handleByPolice(int speed){
      this.strategy.processSpeeding(speed);
  }
}
