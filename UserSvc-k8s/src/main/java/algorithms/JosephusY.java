/*
 * $Id$
 */
package algorithms;

class JosephusY 
{ 
  public static void main(String[] args) 
    { int N = 5;//Integer.parseInt(args[0]); 
      int M = 9;//Integer.parseInt(args[1]); 
      CircularList L = new CircularList(); 
      CircularList.Node x = null; 
      for (int i = 1; i <= N; i++) 
        x = L.insert(x, i); 
      while (x != L.next(x)) 
        { 
          for (int i = 1; i < M; i++) 
            x = L.next(x); 
          L.remove(x); 
        } 
      Out.println("Survivor is " + L.val(x)); 
    } 
} 

