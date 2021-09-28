package cas;

/**
 * 模拟CAS
 */
public class SimulatedCAS{
    private volatile int value;
    public synchronized int comparaAndSwap(int expectedValue, int newValue) {
        int oldvalue = value;
        if (oldvalue == expectedValue) {
            value  = newValue;
        }
        return oldvalue;
    }

}
