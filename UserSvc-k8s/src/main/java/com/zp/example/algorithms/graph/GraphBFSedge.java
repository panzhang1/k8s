/*
 * $Id$
 */
package com.zp.example.algorithms.graph;

import java.util.LinkedList;
import java.util.Queue;

public class GraphBFSedge {
  private Graph G;

  private int cnt;

  private int[] ord, st;
  
  private void searchC(Edge e) {
    Queue<Edge> Q = new LinkedList<Edge>();
    Q.offer(e); ord[e.w] = cnt++;
    while (!Q.isEmpty())
      { e = Q.poll(); int v = e.v, w = e.w;
        st[w] = v;
        AdjList A = G.getAdjList(w);
        for (int t = A.beg(); !A.end(); t = A.nxt())
          if (ord[t] == -1)
          { Q.offer(new Edge(w, t)); ord[t] = cnt++; }
      }

  }
  
  /**
   * print out from w->v
   * @param v
   * @param w
   */
  public void shortestPath(int v, int w) {
    for (int t = w; t !=v; t = this.ST(t)) System.out.print(t + "-");
    System.out.println(v);
  }
  

  GraphBFSedge(Graph G, int v) {
    this.G = G;
    cnt = 0;
    ord = new int[G.V()];
    st = new int[G.V()];
    for (int t = 0; t < G.V(); t++) {
      ord[t] = -1;
      st[t] = -1;
    }
    for (int t = 0; t < G.V(); t++)
      if (ord[t] == -1)
        searchC(new Edge(t, t));
  }

  int order(int v) {
    return ord[v];
  }

  int ST(int v) {
    return st[v];
  }
}
