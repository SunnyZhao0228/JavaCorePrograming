package cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-26 21:12]
 */
public class TwoThreadCompetition implements Runnable{
    private volatile int value;
    public synchronized int comparaAndSwap(int expectedValue, int newValue) {
        int oldvalue = value;
        if (oldvalue == expectedValue) {
            value  = newValue;
        }
        return oldvalue;
    }
    @Override
    public void run() {
         comparaAndSwap(0,1);
    }

    public static void main(String[] args) throws InterruptedException {

        TwoThreadCompetition r = new TwoThreadCompetition();
        r.value = 0;
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.value);
    }
}
