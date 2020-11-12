/*
 * $Id$
 */
package algorithms.unionfind;

public interface UnionFind {
  public boolean connected(int p, int q);

  public int find(int p);

  public void union(int p, int q);
}
