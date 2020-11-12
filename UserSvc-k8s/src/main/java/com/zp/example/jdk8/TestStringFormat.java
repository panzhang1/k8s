package com.zp.example.jdk8;

import java.util.Formatter;

public class TestStringFormat {
    
    public static void main(String[] args) {
        TestStringFormat test = new TestStringFormat();
        test.formatNumber();
    }
    
    private void formatNumber() {
        String text = new Formatter().format("This is %d number", 999999).toString();
        System.out.println(text);
    }
}
