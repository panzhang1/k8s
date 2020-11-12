/*
 * $Id$
 */
package com.zp.example.jdk8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
  interface Hello {
    void sayHello();
  }

  static class HelloLegacyImpl implements Hello {
    @Override
    public void sayHello() {
      System.out.println("hello world in Legacy");
    }
  }
  
  static class HelloBEImpl implements Hello {
    @Override
    public void sayHello() {
      System.out.println("hello world in BE");
    }
  }
  
  
  static class DynamicProxy implements InvocationHandler {
    Object originalObj;

    Object bind(Object originalObj) {
      this.originalObj = originalObj;
      return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
          originalObj.getClass().getInterfaces(), this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      System.out.println("welcome");
      return method.invoke(originalObj, args);
    }
  }

  public static void main(String[] args) {
    Hello hello = (Hello) new DynamicProxy().bind(new HelloLegacyImpl());
    hello.sayHello();
  }
}
