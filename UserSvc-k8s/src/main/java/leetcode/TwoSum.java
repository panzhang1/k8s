/*
 * $Id$
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

public class TwoSum {
    
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    int[] result = new int[2];
 
    for (int i = 0; i < nums.length; i++) {
        if (map.containsKey(nums[i])) {
            int index = map.get(nums[i]);
            result[0] = index;
            result[1] = i;
            break;
        } else {
            map.put(target - nums[i], i);
        }
    }
 
    return result;
  }
  
  public static void main(String[] args) {
    int[] nums = {2, 7, 7, 11, 15};
    TwoSum solution = new TwoSum();
    int[] result = solution.twoSum(nums, 9);
    System.out.println(result[0] + "," + result[1]);
    
    int[] nums2 = {0, 4, 3, 0};
    result = solution.twoSum(nums2, 0);
    System.out.println(result[0] + "," + result[1]);
    
    int[] nums3 = {3, 2, 4};
    result = solution.twoSum(nums3, 6);
    System.out.println(result[0] + "," + result[1]);
    
    int[] nums4 = {4, 3, 3, 5};
    result = solution.twoSum(nums4, 6);
    System.out.println(result[0] + "," + result[1]);
  }
}
