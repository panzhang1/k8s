/*
 * $Id$
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */
public class LongestPalindromicSubstring {
  
  public String longestPalindrome(String s) {
    
    if (s.length() <3) {
      return null;
    }
    
    Map<Character, Integer> pos = new HashMap<>();
    pos.put(s.charAt(0),0);
    pos.put(s.charAt(1),1);
    //i=tail, j=head
    int j=0;
    for (int i = 2; i < s.length(); i++) {
      if (pos.containsKey(s.charAt(i))) {
        
      } else {
        
      }
    }
      
      
    return null;
  }
  public static void main(String[] args) {
    
  

  }

}
