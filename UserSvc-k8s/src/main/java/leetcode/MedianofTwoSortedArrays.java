/*
 * $Id$
 */
package leetcode;

import java.util.Arrays;

/*There are two sorted arrays nums1 and nums2 of size m and n respectively. 
 *Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */
public class MedianofTwoSortedArrays {

  int M = 9;
  int N = 8;
  private int[] getNums1() {
    int[] nums = new int[M];
    for (int i = 0; i< M; i++) {
      nums[i] = 2*(i+1) + 1;
    }
    return nums;
  }
  
  private int[] getNums2() {
    int[] nums = new int[N];
    for (int i = 0; i< N; i++) {
      nums[i] = 3*(i+1) + 1;
    }
    return nums;
  }
  
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int totalLengh = nums1.length + nums2.length;
    
    int i=0,j=0;
    boolean even = (totalLengh % 2) == 0;
    
    int medianIndex = totalLengh / 2 ;
    int previousValue = 0, currentValue=0;
    for (int index=0; index <= medianIndex; index++) {
      previousValue = currentValue;
      if (i >= nums1.length) {
        currentValue = nums2[j++];
      } else if (j >= nums2.length) {
        currentValue = nums1[i++];
      }
      else{
        if ((nums1[i] > nums2[j]) ) {
          currentValue = nums2[j++];
        } else {
          currentValue = nums1[i++];
        }
      }
    }
    if(even) {
      double total = previousValue + currentValue;
      return total /2;
    } else {
      return currentValue;
    }
  }
  
  public static void main(String[] args) {
    MedianofTwoSortedArrays task = new MedianofTwoSortedArrays();
    int[] nums1 = task.getNums1();
    int[] nums2 = task.getNums2();
    System.out.println(Arrays.toString(nums1));
    System.out.println(Arrays.toString(nums2));
    double medium = task.findMedianSortedArrays(nums1, nums2);
    System.out.println(medium);
  }

}
