/*
 * $Id$
 */
package jdk8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class TestJdk {

  public static void main(String[] args) {
    TestJdk testJdk = new TestJdk();
    testJdk.testListIterator();
  }
  
  private List<String> getData() {
    List<String> data = new LinkedList<>();
    for(int i = 0 ; i < 10; i++) {
      data.add("STRING," + i);
    }
    return data;
  } 
  
  public void testIterator() {
    List<String> data = getData();
    Iterator<String> iterator = data.iterator();
    while(iterator.hasNext()) {
      String temp = iterator.next();
      String indexStr = temp.split(",")[1];
      int index = Integer.parseInt(indexStr);
      if(index %2 == 0) {
        iterator.remove();
      }
    }
    
    System.out.println(data);
  }
  
  public void testListIterator() {
    List<String> data = getData();
    ListIterator<String> iterator = data.listIterator();
    while(iterator.hasNext()) {
      String temp = iterator.next();
      String indexStr = temp.split(",")[1];
      int index = Integer.parseInt(indexStr);
      if(index %2 == 0) {
        iterator.set("UPDATED STRING," + index);
      }
    }
    
    System.out.println(data);
  }
  
  public void testMap() {
    Map<String,Long> data = new HashMap<>();
    int count = 1000;
    String KEY = "ABCDEFGHIJ";
    long starttime = System.nanoTime();
    for(int i= 1; i<= count; i++) {
      data.put(KEY + i, (long)i);
    }
    long endtime = System.nanoTime();
    System.out.printf("It costs %s m to create the map\n", (endtime-starttime)/1000000);
    
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    starttime = System.nanoTime();
    long value = data.get(KEY + count);
    endtime = System.nanoTime();
    
    System.out.printf("It costs %s m to get %s from the map", (endtime-starttime)/1000000, value);
  }

}
