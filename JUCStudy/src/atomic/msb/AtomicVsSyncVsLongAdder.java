package atomic.msb;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 对比 加锁，使用原子类AtomicLong 使用LongAddr的效率
 */
public class AtomicVsSyncVsLongAdder {
    private static AtomicLong count1 = new AtomicLong(0);
    private static int count2 = 0;
    private static LongAdder count3 = new LongAdder();


    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    count1.incrementAndGet();
                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        long end = System.currentTimeMillis();

        System.out.println("Atomic :" + count1.get() + "    time:" + (end - start));


        Object obj = new Object();
        // 加synchronized
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    synchronized (obj)  {
                        count2++;
                    }

                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        end = System.currentTimeMillis();

        System.out.println("Synchronized :" + count1.get() + "  time:" + (end - start));


        //使用longAddr
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    count3.increment();

                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        end = System.currentTimeMillis();

        System.out.println("LongAddr :" + count1.get() + "  time:" + (end - start));
    }
}
