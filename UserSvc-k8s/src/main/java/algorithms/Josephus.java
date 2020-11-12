/*
 * $Id$
 */
package algorithms;

public class Josephus {
  int N; // number of person

  int M; // eliminating every Mth person around the circle

  LinkNode header;

  public Josephus(int n, int m) {
    this.N = n;
    this.M = m;
    init();
  }

  private void init() {
    header = new LinkNode(1);
    LinkNode x = header;
    for (int i = 2; i <= N; i++) {
      x = (x.next = new LinkNode(i));
    }
    x.next = header; // let tail point to header and make it be a circle
  }

  public void elect() {
    LinkNode x = header;
    while (x != x.next) {
      // count M-2
      for (int i = 1; i < M - 1; i++) {
        x = x.next;
      }
      // then remove the next data
      System.out.println("remove:" + x.next.val);
      x.next = x.next.next;
      x = x.next;
    }

    Out.println("Survivor is " + x.val);
  }

  public static void main(String[] args) {
    Josephus jose = new Josephus(9,5);
    jose.elect();
  }
}
