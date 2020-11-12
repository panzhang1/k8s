/*
 * $Id$
 */
package algorithms.unionfind;

public class QuickUnion implements UnionFind{
  private int[] id; // access to component id (site indexed)

  private int count; // number of components

  public QuickUnion(int N) {
    // Initialize component id array.
    count = N;
    id = new int[N];
    for (int i = 0; i < N; i++)
      id[i] = i;
  }

  public int count() {
    return count;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }
  
  public int find(int p) {
    while (p != id[p]) p = id[p];
    return p;
  }

  public void union(int p, int q) {
    int pID = find(p);
    int qID = find(q);
    if (pID == qID) {
      System.out.println("Already connected." + p + "--" + q);
      return;
    }
    id[pID] = qID;
    System.out.println(" " + p + "--" + q);
    count--;
  }
}
