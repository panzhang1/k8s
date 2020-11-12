package designpattern;
/*
 * $Id$
 */

public class SingletonFour {
  private SingletonFour() {}

  private static class LazyHolder {
      private static final SingletonFour INSTANCE = new SingletonFour();
  }

  public static SingletonFour getInstance() {
      return LazyHolder.INSTANCE;
  }
}

