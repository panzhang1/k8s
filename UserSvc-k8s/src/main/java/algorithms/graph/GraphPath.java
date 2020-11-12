/*
 * $Id$
 */
package algorithms.graph;

public class GraphPath {
  private Graph G;

  private boolean found;

  private boolean[] visited;

  private boolean searchR(int v, int w) {
    if (v == w)
      return true;
    visited[v] = true;
    AdjList A = G.getAdjList(v);
    for (int t = A.beg(); !A.end(); t = A.nxt())
      if (!visited[t])
        if (searchR(t, w))
          return true;
    return false;
  }

  GraphPath(Graph G, int v, int w) {
    this.G = G;
    found = false;
    visited = new boolean[G.V()];
    found = searchR(v, w);
  }

  boolean exists() {
    return found;
  }
}
