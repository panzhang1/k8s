/*
 * $Id$
 */
package com.zp.example.algorithms;

public class SortManager {

  static int N = 20;

  boolean less(double v, double w) {
    return v < w;
  }

  void exch(double[] a, int i, int j) {
    double t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  boolean compExch(double[] a, int i, int j) {
    boolean exch = false;
    if (less(a[j], a[i])) {
      exch = true;
      exch(a, i, j);
    }
    return exch;
  }

  void selectionSort(double[] a, int l, int r) {
    for (int i = l; i < r; i++) {
      int min = i;
      for (int j = i + 1; j <= r; j++)
        if (less(a[j], a[min]))
          min = j;
      exch(a, i, min);
    }
  }

  void insertSort(double[] a, int l, int r) {
    for (int i = l + 1; i <= r; i++)
      for (int j = i; j > l; j--) {
        // improve 1: if a[j-1] < a[j], break
        if (!compExch(a, j - 1, j)) {
          break;
        }
      }
  }
  
  void insertSort2(double[] a, int l, int r) 
  { int i; 
    for (i = r; i > l; i--) compExch(a, i-1, i); 
    for (i = l+2; i <= r; i++) 
      { int j = i; double v = a[i]; 
        while (less(v, a[j-1])) 
          { a[j] = a[j-1]; j--; } 
        a[j] = v; 
      } 
  }
  

  void shellSort(double[] a, int l, int r) {
    int h;
    //
    for (h = 1; h <= (r - l) / 9; h = 3 * h + 1)
      ;
    //
    for (; h > 0; h /= 3)
      for (int i = l + h; i <= r; i++) {
        int j = i;
        double v = a[i];
        while (j >= l + h && less(v, a[j - h])) {
          a[j] = a[j - h];
          j -= h;
        }
        a[j] = v;
      }
  }

  void bubbleSort(double[] a, int l, int r) {
    for (int i = l; i < r; i++)
      for (int j = r; j > i; j--)
        compExch(a, j - 1, j);
  }
  
  void mergeSort(double[] a, int l, int r) 
  { if (r <= l) return; 
    int m = (r+l)/2; 
    mergeSort(a, l, m); 
    mergeSort(a, m+1, r); 
    merge(a, l, m, r); 
  } 
  
  void merge(double[] a, int l, int m, int r) 
  { 
    double aux[] = new double[N];
    int i, j; 
    for (i = m+1; i > l; i--) aux[i-1] = a[i-1]; 
    for (j = m; j < r; j++) aux[r+m-j] = a[j+1]; 
    for (int k = l; k <= r; k++) 
      if (less(aux[j], aux[i])) 
          a[k] = aux[j--]; else a[k] = aux[i++]; 
  } 

  double[] getRandomArray() {
    double a[] = new double[N];
    for (int i = 0; i < N; i++)
      a[i] = Math.random();
    return a;
  }

  public static void main(String[] args) {
    SortManager manager = new SortManager();
    double data[] = manager.getRandomArray();

    //
    manager.mergeSort(data, 0, N - 1);
    System.out.println("after sort:");
    for (double d : data) {
      System.out.println(d);
    }
  }

}
