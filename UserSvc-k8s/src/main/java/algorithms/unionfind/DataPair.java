/*
 * $Id$
 */
package algorithms.unionfind;

public class DataPair{
  private int p;
  private int q;
  
  public DataPair(int p, int q) {
    this.p = p;
    this.q = q;
  }
  
  public int getP() {
    return p;
  }
  public void setP(int p) {
    this.p = p;
  }
  public int getQ() {
    return q;
  }
  public void setQ(int q) {
    this.q = q;
  }
}
