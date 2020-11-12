/*
 * $Id$
 */
package test;

public class StaticDispatch {

  static abstract class Human {

  }

  static class Man extends Human {

  }

  static class Woman extends Human {

  }
  
  public void sayHello(Human guy) {
    System.out.println("hello, guy!");
  }
  
  public void sayHello(Man guy) {
    System.out.println("hello, gentlman!");
  }
  
  public void sayHello(Woman guy) {
    System.out.println("hello, lady!");
  }
  public static void main(String[] args) {
    StaticDispatch test = new StaticDispatch();
    Human man = new Man();
    Human woman = new Woman();
    
    test.sayHello((Man)man);
    test.sayHello((Woman)woman);
  }

}
