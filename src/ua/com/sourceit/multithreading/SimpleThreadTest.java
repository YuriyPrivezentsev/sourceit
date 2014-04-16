package ua.com.sourceit.multithreading;

/**
 * Created by yuriy on 13.04.14.
 */
public class SimpleThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        });
        thread.notify();
        thread.interrupt();
    }
}
