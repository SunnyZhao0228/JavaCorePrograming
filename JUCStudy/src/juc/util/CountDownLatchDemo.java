package juc.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchDemo {
    public static final int COUNT = 10;
    public static final int ITERATIONS = 10000;
    public static CountDownLatch countDownLatch = new CountDownLatch(10);
    public static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) {
        java.util.concurrent.atomic.AtomicLong  sum = new AtomicLong(0);
        for (int i = 0; i < COUNT; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < ITERATIONS; i++) {
                        lock.lock();
                        try {
                            sum.incrementAndGet();
                            // 这里可以执行其他需要保护
                        } finally {
                            lock.unlock();
                        }
                    }
                    countDownLatch.countDown();
                }
            }).start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All threads finished");
        System.out.println("sum: " + sum);
    }
}
