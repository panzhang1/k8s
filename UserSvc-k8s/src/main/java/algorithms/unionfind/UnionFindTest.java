/*
 * $Id$
 */
package algorithms.unionfind;

import java.util.ArrayList;
import java.util.List;

import algorithms.unionfind.DataPair;

public class UnionFindTest {
  private static List<DataPair> prepareData() {
    List<DataPair> list = new ArrayList<DataPair>();
    DataPair pair = new DataPair(3,4);
    list.add(pair);
    
    pair = new DataPair(4,9);
    list.add(pair);
    
    pair = new DataPair(8,0);
    list.add(pair);
 
    pair = new DataPair(2,3);
    list.add(pair);
    
    pair = new DataPair(5,6);
    list.add(pair);
  
    pair = new DataPair(2,9);
    list.add(pair);
    
    pair = new DataPair(5,9);
    list.add(pair);
 
    pair = new DataPair(7,3);
    list.add(pair);
    
    pair = new DataPair(4,8);
    list.add(pair);

    pair = new DataPair(5,6);
    list.add(pair);
 
    pair = new DataPair(0,2);
    list.add(pair);
    
    pair = new DataPair(6,1);
    list.add(pair);
    
    
    return list;
  }
  public static void main(String[] args) {
    int N = 10;
    //U
    UnionFind uf = new QuickFind(N);
    for (DataPair pair : prepareData()) {
      uf.union(pair.getP(), pair.getQ());
    }
    
    UnionFind uf2 = new WeightedQuickUnion(N);
    for (DataPair pair : prepareData()) {
      uf2.union(pair.getP(), pair.getQ());
    }
  }

}
