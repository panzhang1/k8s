/*
 * $Id$
 */
package designpattern;

import java.util.ArrayList;
import java.util.List;

public class FactoryPattern {

  public static void main(String[] args) {
    List<Human> humans = new ArrayList<>();
    humans.add(HumanFactory.createBoy());
    humans.add(HumanFactory.createGirl());
    
    for (Human human : humans) {
      human.Talk();
      human.Walk();
    }
  }

}

interface Human {
  public void Talk();
  public void Walk();
}


class Boy implements Human{
  @Override
  public void Talk() {
      System.out.println("Boy is talking...");        
  }

  @Override
  public void Walk() {
      System.out.println("Boy is walking...");
  }
}

class Girl implements Human{

  @Override
  public void Talk() {
      System.out.println("Girl is talking...");   
  }

  @Override
  public void Walk() {
      System.out.println("Girl is walking...");
  }
}

class HumanFactory {
  public static Human createBoy(){
     return new Boy();
  }
  
  public static Human createGirl(){
    return new Girl();
 }
}
