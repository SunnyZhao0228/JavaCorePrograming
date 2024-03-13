package juc.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoqw
 * @date 2023/02/04
 */
public class CyclicBarrierSample {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 50; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    go();
                }
            });
        }


        threadPool.shutdown();
    }

    public static void go() {
        System.out.println(Thread.currentThread().getName() + ": ready");
        try {
            cyclicBarrier.await(); // 设置屏障点。当累计5个线程都准备好后，才运行后面的代码
            System.out.println(Thread.currentThread().getName() + ": start run");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }
}
