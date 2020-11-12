/*
 * $Id$
 */
package com.zp.example.jdk8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class TestCollection {

  public static void main(String[] args) {
    TestCollection test = new TestCollection();
  }

  public void testIterator() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    
    Iterator<Integer> iterator = list.iterator();
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
      iterator.remove();
    }
    System.out.println("size:" + list.size() + ", list:" + list.toString());
  }
  
  private List<String> getListData() {
    List<String> data = new ArrayList<>();
    for(int i = 0 ; i< 100; i++) {
      data.add("S" + i);
    }
    
    return data;
  }
  

  
  public void testSubList() {
    List<String> list = getListData();
    List<String> subList = list.subList(0, 20);
    
    System.out.println("1:" + subList);
    //list.remove(99);
    list.set(0, "C0");
    System.out.println("2:" + list);
    System.out.println("3:" + subList);
  }
  

  
  public void testSortedSet() {
    SortedSet<String> set = new TreeSet<>();
    
    set.add("Z1");
    set.add("A1");
    set.add("Z2");
    set.add("B3");    
    set.add("D1");
    set.add("K1");
    set.add("F2");
    set.add("G3");  
    
    System.out.println("set:" + set);
    
    SortedSet<String> partSet = set.subSet("D1", "M1");
    System.out.println("partSet:" + partSet);
    
    set.add("F10");
    set.add("L5");
    set.add("O4");
    
    System.out.println("partSet after change:" + partSet);
    
    partSet.remove("K1");
    
    System.out.println("set after change:" + set);
  }

}
