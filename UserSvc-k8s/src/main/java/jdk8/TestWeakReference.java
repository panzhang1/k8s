/*
 * $Id$
 */
package jdk8;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class TestWeakReference {

  public static void main(String[] args) {
    TestWeakReference test = new TestWeakReference();
    test.testSoftReference();
  }
  
  private void testSoftReference() {
    String a = new String("a");  
    ReferenceQueue queue = new  ReferenceQueue();  
    WeakReference<String> ref = new WeakReference<>(a,queue);
    System.out.println(ref.get());
    a = null;
    //System.gc();  
    System.out.println(ref.get());
    Reference r = null;
    while( (r= queue.poll()) != null) {
      System.out.println(r);
    }
  }
  
  private void testWeakHashMap() {
    String a = new String("a");  
    String b = new String("b");  
    Map weakmap = new WeakHashMap();  
    Map map = new HashMap();  
    map.put("aaa",a);  
    map.put("bbb",b);  
      
    weakmap.put("aaa",a);  
    weakmap.put("bbb",b);  
      
    map.remove("aaa");  
      
    a=null;  
    b=null;  
      
    System.gc();  
    Iterator i = map.entrySet().iterator();  
    while (i.hasNext()) {  
        Map.Entry en = (Map.Entry)i.next();  
        System.out.println("map:"+en.getKey()+":"+en.getValue());  
    }  

    Iterator j = weakmap.entrySet().iterator();  
    while (j.hasNext()) {  
        Map.Entry en = (Map.Entry)j.next();  
        System.out.println("weakmap:"+en.getKey()+":"+en.getValue());     
    }  
  }

}
