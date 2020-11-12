/*
 * $Id$
 */
package com.zp.example.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class TestMap {
  static final int MAXIMUM_CAPACITY = 1 << 30;
  
  public static void main(String[] args) {
    TestMap testMap = new TestMap();
    testMap.testTreeMap();
  }

  public void testTreeMap() {
    TreeMap<Integer, String> map = new TreeMap<>();
    List<Integer> keys = new ArrayList<>();
    for (int i= 0 ;i < 10; i++) {
      keys.add(2*i + 1);
    }
    Collections.shuffle(keys);
    
    for (Integer key : keys) {
      map.put(key, key.toString());
    }
    
    System.out.println(map.keySet());
    
    System.out.println("lower:" + map.lowerKey(14));
    System.out.println("floor:" + map.floorKey(14));
    System.out.println("ceiling:" + map.ceilingKey(14));
    System.out.println("higher:" + map.higherKey (14));
  }
  
  
  static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
  
  public void testHashTableSize() {
    System.out.println(tableSizeFor(20));
    System.out.println(tableSizeFor(32));
  }
  
  public void testOrder() {
    Map<String,Integer> map = new LinkedHashMap<>(16,0.75f,true);
    map.put("B", 1);
    map.put("C", 2);
    map.put("A", 3);
    map.put("E", 4);
    map.put("D", 5);

    
    System.out.println(map.keySet());
    map.put("C", 3);
    map.get("B");
    System.out.println(map.keySet());
    
  }
  
  static final int hash(Object key) {
    int h;
    int value = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    System.out.println(key.hashCode() + "," + value);
    
    return value;
  }
  
  public void testHashMap() {
    int length = 64;
    String key1 = "a";
    int index1 = (length - 1) & hash(key1);
    System.out.println(key1 + ", index:" + index1);
    
    String key2 = "b";
    int index2 = (length - 1) & hash(key2);
    System.out.println(key2 + ", index:" + index2);
  }
  
  public void testMap() {
    Map<Integer,String> map = new HashMap<>();
    map.put(10, "8");
    map.put(1, "10");
    map.put(8, "3");
    map.put(2, "5");
    map.put(7, "4");
    map.put(4, "2");
    
    /*
    for(Iterator<Integer> keys = map.keySet().iterator(); keys.hasNext();) {
      if(keys.next() %2 == 0) {
        keys.remove();
      }
    }*/
    
    for(Iterator<String> values = map.values().iterator(); values.hasNext();) {
      if(values.next().equals("10")) {
        values.remove();
      }
    }
    
    for(Entry<Integer, String> entry : map.entrySet()) {
      System.out.println(entry.getKey() + "," + entry.getValue());
      entry.setValue(entry.getValue() + "a");
    }
    
    System.out.println(map);
  }
  
  public void testRemoveNull() {
    Map<String,String> map = new HashMap<>();
    map.put("K1", "V1");
    map.put("K2", null);
    map.put(null, "V3");
    
    Set<String> keys = map.keySet();
    System.out.println("Keys:" + keys);
    keys.remove(null);
    System.out.println("Keys 2:" + keys);
  }
}
