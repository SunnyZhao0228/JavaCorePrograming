package juc.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoqw
 * @date 2023/02/04
 */
public class CountDownSample {
    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        CountDownLatch cdl = new CountDownLatch(10000);
        for (int i = 1; i <= 10000; i++) {
            final int index = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    synchronized(CountDownSample.class) {
                        try {
                            count = count + index;
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            cdl.countDown(); // 倒计时-1
                        }
                    }
                }
            });
        }
        //
        try {
            cdl.await(); // 阻塞当前线程，知道cdl=0的时候再继续
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(count);
        threadPool.shutdown();
    }
}
