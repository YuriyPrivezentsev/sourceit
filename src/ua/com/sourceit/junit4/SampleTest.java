package ua.com.sourceit.junit4;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Test class for TestableSample
 */
public class SampleTest {
    //Testable object is kept here just to show usage of Before and After methods
    //Normally it would be created inside the tests since it does not require any preconditions
    //In fact it would not be created at all, since all its methods could have been static.
    private TestableSample inspectedObject;

    /**
     * Initialization method to run before each test.
     */
    @Before
    public void setUp(){
        inspectedObject = new TestableSample();
    }

    /**
     * Tear down method to run after each test.
     */
    @After
    public void tearDown(){
        inspectedObject = null;
    }

    /**
     * Plain success test. We know input and outcome, just test that they match.
     * Practically it is hard to imagine failure test here.
     */
    @Test
    public void testSuccess(){
        double divisionResult = 0;
        try {
            divisionResult = inspectedObject.divide(10, 5);
        } catch (DivisionByZeroException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(divisionResult,2.0);
    }

    /**
     * Test that expected exception is thrown after the expected input
     * @throws DivisionByZeroException - the expected exception when the divisor is zero
     */
    @Test(expected = DivisionByZeroException.class)
    public void testException() throws DivisionByZeroException {
        inspectedObject.divide(10,0);
    }

    /**
     * Test which validates that function fits into timeout. Timeout is a bit bigger than
     * the actual timeout in the test, since it takes some time for preparation.
     */
    @Test(timeout = 11)
    public void testSleepySuccess(){
        Assert.assertTrue(inspectedObject.sleepySample(10));
    }

    /**
     * Test the failure execution path of the sleeper method. Mind the usage of Callable and ThreadPool
     * @throws ExecutionException - propagating the computation exception, which is none in our case
     * @throws InterruptedException - propagating the exception of thread sleep, local to this method
     */
    @Test
    public void testSleepyFailure() throws ExecutionException, InterruptedException {
        final Callable<Boolean> runSleeper = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return inspectedObject.sleepySample(1000);
            }
        };

        final ExecutorService threadPool = Executors.newFixedThreadPool(1);
        final Future<Boolean> result = threadPool.submit(runSleeper);

        Thread.sleep(10);
        threadPool.shutdownNow();

        Assert.assertFalse(result.get());
    }
}
