package com.zp.example.test;

import java.util.Timer;

public class MyTaskTest {
    public static void main(String[] args) {
        Timer t = new Timer();
        MyTask mTask = new MyTask();
        // This task is scheduled to run every 10 seconds

        t.scheduleAtFixedRate(mTask, 0, 10000);
    }
}
