/*
 * $Id$
 */
package com.zp.example.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class Test {

  public void testDecimal() {
    String value = "10000.11511";
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    System.out.println(bd + "," + bd.scale() + "," + bd.precision());
   
    String value2 = "-10000.11511";
    BigDecimal bd2 = new BigDecimal(value2);
    bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
    System.out.println(bd2 + "," + bd2.scale() + "," + bd2.precision());
    
    BigDecimal bd3 = new BigDecimal(10.12);
    System.out.println(bd3);
    
    BigDecimal bd4 = new BigDecimal("10.12");
    System.out.println(bd4);
  }
  
  public void testStringMatcher() {
    String p = ".*\\." + "csv";
    String fileName = ".csv";
    if (fileName.matches(p)) {
      System.out.println("match");
    } else {
      System.out.println("not match");
    }
  }
  
  public void testMsgFormat() {
    String key = "Template doesn''t define column for key: {0}.";
    MessageFormat format = new MessageFormat(key, Locale.ENGLISH);
    Object[] array = new Object[]{"effectiveStartDate"};
    String s = format.format(array);
    System.out.println(s);
  }
  
  public void testEquals() {
    Test test1 = null;
    Test test2 = null;
    
    System.out.println("result1:" + (test1 == test2));
    
    boolean result = Objects.equals(test1, test2);
    System.out.println("result2:" + result);
  }
  
  public void testLong() {
    Long l = null;
    long l1 = l;
    System.out.println(l1);
  }
  
  public void testRandom() {
    Random random = new Random();
    System.out.println(random.nextInt(20));
  }
 
  
  private String csvFormat(String str) {
    String[] array = str.split(",");
    StringBuilder sb = new StringBuilder();
    for(int i = 0 ; i < array.length; i++) {
      sb.append("\"" + array[i] + "\",");
    }
    return sb.substring(0, sb.length()-1) + " \r\n";
  }
  
  public void testArray() {
    int length = 10;
    int[] a = new int[10];
    for (int i = 0 ; i< length; i++) {
      a[i] = i;
    }
  }
  
  public void testMod() {
    int size = 16;
    for(int i = 64; i<72; i++) {
      System.out.println(i + "," + i%size + "," + (i & (size-1)));
    }
  }
  
  public void testReplace() {
    String str1 = "externalCode,effectiveStartDate,externalName,numberValue,ref.externalCode";
    String newStr = csvFormat(str1);
    
    String str = "\"externalCode\",\"effectiveStartDate\",\"externalName\",\"numberValue\",\"ref.externalCode\" \r\n";
    
    if(newStr.equals(str)) {
      System.out.println("result is correct");
    }
    System.out.println(newStr);
  }
  
  public void testToString() {
    List<String> codes = new ArrayList<>();
    codes.add("S1");
    codes.add("S2");
    codes.add("S3");
    
    String s = codes.toString();
    System.out.println(s);
  }
  
  public void testSB() {
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<10;i++) {
      sb.append("Field").append(i).append("|");
    }
    sb.delete(sb.length() -1, sb.length());
    System.out.println(sb);
  }
  
  public double testDouble() {
    Double total = new Double(2+3);
    double result = total/2;
    return result;
  }
  
  public void testStringFormat() {
    Object[] args = new String[3];
    args[0] = "s1";
    args[1] = "s2";
    args[2] = "s3";
    
    String s = String.format("This is %s, my com.zp.example.test %s. Ok %s com.zp.example.test", args);
    System.out.println(s);
  }
  
 public void testDecimalFormat() {
   DecimalFormat formatter = new DecimalFormat();
   DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
   symbols.setGroupingSeparator(' ');
   formatter.setDecimalFormatSymbols(symbols);
   
   try {
    Number number = formatter.parse("1000000.56");
    System.out.println(number);
    String text = formatter.format(number);
    System.out.println(text);
  } catch (ParseException e) {
    e.printStackTrace();
  }   
 } 
 
 private void testNumberFormat() {
   NumberFormat format = NumberFormat.getInstance();
   try {
    Number n = format.parse("R10000000");
    System.out.println(n.longValue());
  } catch (ParseException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
 }

 public void testCompanyStatus() {
   int result = 6199 & 0x0002;
   System.out.println(result);
 }
 
  public static void main(String[] args) {
    Test test = new Test();
    test.testCompanyStatus();
  }
  
}
