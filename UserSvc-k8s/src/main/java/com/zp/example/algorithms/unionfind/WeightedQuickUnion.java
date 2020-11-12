/*
 * $Id$
 */
package com.zp.example.algorithms.unionfind;

public class WeightedQuickUnion implements UnionFind{
  private int[] id; // access to component id (site indexed)
  private int[] sz; // access to component id (site indexed)

  private int count; // number of components

  public WeightedQuickUnion(int N) {
    // Initialize component id array.
    count = N;
    id = new int[N];
    sz = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
      sz[i] = 1; 
    }
  }

  public int count() {
    return count;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public int find(int p) {
    while (p != id[p]) {
      id[p] = id[id[p]];  //compress the tree path. set the parent to parent's parent
      p = id[p];
    }
    return p;
  }

  public void union(int p, int q) {
    int pID = find(p);
    int qID = find(q);
    if (pID == qID) {
      System.out.println("Already connected." + p + "--" + q);
      return;
    }
    
    if (sz[pID] < sz[qID]) { 
      id[pID] = qID; sz[qID] += sz[pID]; 
    }  
    else { 
      id[qID] = pID; sz[pID] += sz[qID]; 
    }  
    
    System.out.println(" " + p + "--" + q);
    count--;
  }
}
