/*
 * $Id$
 */
package com.zp.example.leetcode;

import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
/*
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
*/
public class LongestSubString {

  //O^2
  public int lengthOfLongestSubstring2(String s) {
    int maxLength = 0;
    char[] chars = s.toCharArray();
    String candidate = "";
    for (int i=0; i < s.length(); i++) {
      String temp = "";
      for (int j=i; j < s.length(); j++) {
        //if index j is included in temp
        if (temp.indexOf(chars[j]) == -1) {
          temp = temp + chars[j];
          if (temp.length() > maxLength) {
            maxLength = temp.length();
            candidate = temp;
          }
        } else {
          break;
        }
      }
    }
    System.out.println("candidate :" + candidate);
    return maxLength;
  }

  public int lengthOfLongestSubstring(String s) {
    if (s.length()==0) return 0;
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    int max=0;
    //j:startIndex; i:endIndex
    for (int i=0, j=0; i<s.length(); ++i){
        if (map.containsKey(s.charAt(i))){
            j = Math.max(j,map.get(s.charAt(i))+1);
        }
        map.put(s.charAt(i),i);
        max = Math.max(max,i-j+1);
    }
    return max;
  }
  
  public int lengthOfLongestSubstring1(String s) {
    int maxLength = 0;
    char[] chars = s.toCharArray();
    String candidate = "";
    int i = 0;
    int length = s.length();
    
    int[] pos = new int[256];
    Arrays.fill(pos, -1);
    while (i < length) {
      int j = i;
      while (j < length) {
        boolean findRepeat = false;
        int index = chars[j];
        if (pos[index] == -1) {
          pos[index] = j;
          j ++;
          
          if (j == length) {
            findRepeat = true;
          }
        } else {
          findRepeat = true;
        }
        //if it can go to the end, it means all the rest has no repeat
        if (findRepeat) {
          if (maxLength < j-i) {
            maxLength = j-i;
            candidate = s.substring(i, j);
          }
          i = pos[index] + 1;
          Arrays.fill(pos, -1);
          break;
        }
      }
    }
    System.out.println("candidate :" + candidate);
    return maxLength;
  }
  
  public static void main(String[] args) {
    LongestSubString task = new LongestSubString();

    task.lengthOfLongestSubstring("abaabcde");
   
    int result = task.lengthOfLongestSubstring("abcabcbb");
    Assert.assertEquals(result, 3);
    
    result = task.lengthOfLongestSubstring("bbbbbb");
    Assert.assertEquals(result, 1);
    
    result = task.lengthOfLongestSubstring("c");
    Assert.assertEquals(result, 1);
    
    result = task.lengthOfLongestSubstring("pwwkew");
    Assert.assertEquals(result, 3);
    
    result = task.lengthOfLongestSubstring("au");
    Assert.assertEquals(result, 2);
    
    result = task.lengthOfLongestSubstring("aab");
    Assert.assertEquals(result, 2);
  }

}
