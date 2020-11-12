/*
 * $Id$
 */
package algorithms;

import java.util.Stack;

public class PostfixManager {

  public int executePostFix(String input) {
    char[] a = input.toCharArray();
    int N = a.length;
    Stack<Integer> s = new Stack<Integer>();
    for (int i = 0; i < N; i++) {
      if (a[i] == '+')
        s.push(s.pop() + s.pop());
      if (a[i] == '*')
        s.push(s.pop() * s.pop());
      if ((a[i] >= '0') && (a[i] <= '9'))
        s.push(0);
      while ((a[i] >= '0') && (a[i] <= '9')) {
        int v1 = 10 * s.pop();
        int v2 = (a[i++] - '0');
        int v3 = v1 + v2;
        s.push(v3);
      }
    }
    return s.pop();
  }

  public String InfixToPostfix(String input) {

    StringBuilder sb = new StringBuilder();
    char[] a = input.toCharArray();
    int N = a.length;
    Stack s = new Stack();
    for (int i = 0; i < N; i++) {
      if (a[i] == ')')
        sb.append(s.pop() + " ");
      if ((a[i] == '+') || (a[i] == '*'))
        s.push(a[i]);
      if ((a[i] >= '0') && (a[i] <= '9'))
        sb.append(a[i] + " ");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    PostfixManager postfixManager = new PostfixManager();
    String infix = "(5 * ( ( (9 + 8) * (4 * 6) ) + 7) )";
    String postfix = postfixManager.InfixToPostfix(infix);
    System.out.println(postfix);
    int result = postfixManager.executePostFix(postfix);
    System.out.println("result is:" + result);
  }

}
