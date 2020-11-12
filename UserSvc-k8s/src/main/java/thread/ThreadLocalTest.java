package thread;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    public static ThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<Integer>();
    public static void main(String[] args) throws InterruptedException {
        inheritableThreadLocal.set(10);
        Thread thread = new MyThread();
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        for (int i=0; i< 5; i++) {
            inheritableThreadLocal.set(11 + i);
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("Main = " + inheritableThreadLocal.get());
        }
        inheritableThreadLocal.remove();
        System.out.println("Main = " + inheritableThreadLocal.get());
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i=0; i< 10; i++) {
                System.out.println(i + "MyThread = " + inheritableThreadLocal.get());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
