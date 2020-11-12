/*
 * $Id$
 */
package designpattern;

public class TemplateMethodPattern {

  public static void main(String[] args) {
    Car car = new Car();
    testVehicle(car);

    Truck truck = new Truck();
    testVehicle(truck);
  }

  public static void testVehicle(Vehicle v){
    v.testYourVehicle();
  }
}

abstract class Vehicle {
  //set to protected so that subclass can access
  protected boolean status;

  abstract void start();
  abstract void run();
  abstract void stop();

  public void testYourVehicle(){
      start();
      if(this.status){
          run();
          stop();
      }   
  }
}

class Car extends Vehicle {
  
  @Override
  void start() {
      this.status = true;
  }

  @Override
  void run() {
      System.out.println("Run fast!");

  }

  @Override
  void stop() {
      System.out.println("Car stop!");
  }
}

class Truck extends Vehicle {
  
  @Override
  void start() {
      this.status = true;
  }

  @Override
  void run() {
      System.out.println("Run slowly!");
  }

  @Override
  void stop() {
      System.out.println("Truck stop!");

  }
}