package threadcore.study.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author zhaoqw
 * @date 2025/1/13
 */
public class ThreadUnSafeDemo {
    private int count = 0;
    public void add() {
        this.count ++;
    }

    public int getCount() {
        return count;
    }

    public void add10K() {
        for (int i = 0; i < 100000; i++) {
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int threadNum = 1000;
        ThreadUnSafeDemo threadUnSafeDemo = new ThreadUnSafeDemo();
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(8);
        for (int i = 0; i < threadNum; i++) {
            scheduledExecutorService.execute(() ->{
                try {
                    threadUnSafeDemo.add();
                    countDownLatch.countDown();
                    System.out.println(threadUnSafeDemo.getCount());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }


        countDownLatch.await();
        scheduledExecutorService.shutdown();
        System.out.println(threadUnSafeDemo.getCount());
        calc10K();
    }

    public static void calc10K() throws InterruptedException {
        final ThreadUnSafeDemo threadUnSafeDemo = new ThreadUnSafeDemo();
        Thread thread = new Thread(() -> {
            threadUnSafeDemo.add10K();
        });

        Thread thread2 = new Thread(() -> {
            threadUnSafeDemo.add10K();
        });

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(threadUnSafeDemo.count);
    }

}
