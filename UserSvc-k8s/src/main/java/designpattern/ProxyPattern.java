/*
 * $Id$
 */
package designpattern;

public class ProxyPattern {

  public static void main(String[] args) {
    Sourceable source = new Source();  
    Sourceable obj = new Proxy(source);  
    obj.method();
    obj.method();
    obj.method();
  }
  
  interface Sourceable {  
    public void method();  
  } 

  static class Source implements Sourceable {  
    
    @Override  
    public void method() {  
        System.out.println("the original method!");  
    }  
  }

  static class Proxy implements Sourceable {  
    
    private Sourceable source;  
      
    public Proxy(Sourceable source){  
        super();  
        this.source = source;  
    }  
    @Override  
    public void method() {  
       System.out.println("proxy do something");
       source.method();  
    }  
  }  
}


