package ua.com.sourceit.multithreading;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuriy on 13.04.14.
 */
public class SynchronizedCounterTest {
    private static final String MESSAGE_STUB = "%s counter value %d";
    private Integer counter = 0;
    private List<String> messages = new LinkedList<>();

    public int increment(){
        //We cannot synchronize on counter here, since increment changes the counter object and thus our synch section becomes unsynchronized
        synchronized (MESSAGE_STUB){
            counter++;
            messages.add(String.format(MESSAGE_STUB,Thread.currentThread().getName(), counter));
            System.out.println(String.format(MESSAGE_STUB,Thread.currentThread().getName(), counter));
            return counter;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int numberOfTestSteps = 10;
        final SynchronizedCounterTest synchronizedCounter = new SynchronizedCounterTest();

        class CountingThread extends Thread{
            public CountingThread(String threadName) {
                super(threadName);
            }

            @Override
            public void run() {
                int count;
                do{
                    count = synchronizedCounter.increment();
                } while (count < numberOfTestSteps);
            }
        }

        final CountingThread countingThread1 = new CountingThread("Thread 1");
        final CountingThread countingThread2 = new CountingThread("Thread 2");

        countingThread1.start();
        countingThread2.start();

        countingThread1.join();
        countingThread2.join();

//        synchronizedCounter.printMessages();

    }

    public void printMessages(){
        for (String message : messages) {
            System.out.println(message);
        }
    }
}
