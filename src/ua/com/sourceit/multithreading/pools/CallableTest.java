package ua.com.sourceit.multithreading.pools;

import java.util.concurrent.*;

/**
 * Created by yuriy.privezentsev on 4/17/14.
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Hi from " + Thread.currentThread().getName());
                return 42;
            }
        };

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        ScheduledFuture<Integer> scheduled = scheduledExecutorService.schedule(callable, 15, TimeUnit.SECONDS);

        System.out.println("We can take a rest here");

        Integer integer = scheduled.get();
        System.out.println("integer = " + integer);

//        scheduledExecutorService.shutdown();
        System.out.println("Everything is done");
    }
}
