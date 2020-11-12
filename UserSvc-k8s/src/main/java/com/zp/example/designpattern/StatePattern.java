/*
 * $Id$
 */
package com.zp.example.designpattern;

public class StatePattern {

  public static void main(String[] args) {
    State rich = new Rich();
    State poor = new Poor();
    StateContext sc = new StateContext();
    sc.saySomething();
    sc.changeState(rich);
    sc.saySomething();
    sc.changeState(poor);
    sc.saySomething();
    sc.changeState(rich);
    sc.saySomething();
  }

}


interface State {
  public void saySomething();
}

class Rich implements State{
  @Override
  public void saySomething() {
      System.out.println("I'm rick currently, and play a lot.");;
  }
}

class Poor implements State{
  @Override
  public void saySomething() {
      System.out.println("I'm poor currently, and spend much time working.");
  }
}

class StateContext {
  private State currentState;

  public StateContext(){
      currentState = new Poor();
  }

  public void changeState(State newState){
      this.currentState = newState;
  }

  public void saySomething(){
      this.currentState.saySomething();
  }
}