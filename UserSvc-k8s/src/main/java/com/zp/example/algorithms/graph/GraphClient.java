/*
 * $Id$
 */
package com.zp.example.algorithms.graph;

public class GraphClient {

  public static void main(String[] args) {
    GraphClient client = new GraphClient();
    client.testGraphBFSearch();

  }
  
  private void testGraphBFSearch(){
    Graph G = initGraph();
    GraphBFSedge bfs = new GraphBFSedge(G,0);
    
    for(int i=0; i< G.V() ; i++) {
      System.out.println("Vertex " + i + ": order:" + bfs.order(i) + ",parent:" + bfs.ST(i));
    }
    
    bfs.shortestPath(0, 4);
  }

  private Graph initGraph(){
    Graph g = new Graph(8,false);
    g.insert(new Edge(0,2));
    g.insert(new Edge(0,5));
    g.insert(new Edge(0,7));
    
    g.insert(new Edge(2,6));
    
    g.insert(new Edge(5,3));
    g.insert(new Edge(5,4));
    
    g.insert(new Edge(7,1));
    g.insert(new Edge(7,4));
    
    g.insert(new Edge(6,4));
    
    g.insert(new Edge(3,4));
    
    return g;
  }
}
