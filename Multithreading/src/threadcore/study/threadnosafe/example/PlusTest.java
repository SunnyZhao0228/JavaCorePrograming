package threadcore.study.threadnosafe.example;

import java.util.concurrent.CountDownLatch;

public class PlusTest {
    private static int MAX_THREAD = 100;
    private static int MAX_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(MAX_THREAD);
        NoSafePlus noSafePlus = new NoSafePlus();
        Runnable runnable = () -> {
            for (int i = 0; i < MAX_COUNT; i++) {
                noSafePlus.selfSafeIncrement();
            }
            countDownLatch.countDown();
        };
        for (int i = 0; i < MAX_THREAD; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
        countDownLatch.await();
        System.out.println("期望结果: " + MAX_THREAD * MAX_COUNT);
        System.out.println("实际结果: " + noSafePlus.getCount());
        System.out.println("差距: " + (MAX_THREAD * MAX_COUNT - noSafePlus.getCount()));

    }
}
