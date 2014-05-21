package ua.com.sourceit.junit4;

/**
 * Class containing a couple of methods which we are going to test
  */
public class TestableSample {

    /**
     * Division function that.
     * @param dividend
     * @param divisor
     * @return - the result of division
     * @throws DivisionByZeroException - in case if divisor iz zero
     */
    public double divide(float dividend, float divisor) throws DivisionByZeroException {
        if(divisor == 0){
            throw new DivisionByZeroException();
        }
        return dividend/divisor;
    }

    /**
     * Function puts current thread to sleep.
     * @param timeout - time of sleep
     * @return true if thread had enought time to sleep, false if it was interrupted
     */
    public boolean sleepySample(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }
}
