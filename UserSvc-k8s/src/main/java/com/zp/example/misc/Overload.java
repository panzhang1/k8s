/*
 * $Id$
 */
package com.zp.example.misc;

import java.io.Serializable;

public class Overload {

  public void sayHello(Object arg) {
    System.out.println("hello object");
  }
  
//  public void sayHello(int arg) {
//    System.out.println("hello int");
//  }
//  
//  public void sayHello(long arg) {
//    System.out.println("hello long");
//  }
  
//  public void sayHello(Character arg) {
//    System.out.println("hello Character");
//  }

//  public void sayHello(char arg) {
//    System.out.println("hello char");
//  }
  
  public void sayHello(char... arg) {
    System.out.println("hello char...");
  }
  
  public void sayHello(Serializable arg) {
    System.out.println("hello Serializable");
  }
  
  public static void main(String[] args) {
    Overload overload = new Overload();
    overload.sayHello('a');
  }

}
