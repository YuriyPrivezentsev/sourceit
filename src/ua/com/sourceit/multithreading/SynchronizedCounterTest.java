package ua.com.sourceit.multithreading;

/**
 * Created by yuriy on 13.04.14.
 */
public class SynchronizedCounterTest {
    private Integer counter = 0;

    public int increment(){
        synchronized (counter){
            counter++;
            return counter;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int numberOfTestSteps = 15;
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
                    System.out.format("%s counter value %d\n", Thread.currentThread().getName(), count);
                } while (count < numberOfTestSteps);
            }
        }

        final CountingThread countingThread1 = new CountingThread("Thread 1");
        final CountingThread countingThread2 = new CountingThread("Thread 2");

        countingThread1.start();
        countingThread2.start();

        countingThread1.join();
        countingThread2.join();

    }
}
