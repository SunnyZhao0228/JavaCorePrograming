package cputask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟一个并发请求场景
 */
public class ThreadDemo {

    private static int j = 0;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    static class IncrTask implements Runnable {
        CountDownLatch start;
        CountDownLatch end;

        public IncrTask(CountDownLatch start, CountDownLatch end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                // await() 方法具有阻塞作用,等待countDown为0之后放行
                start.await();
                for (int i = 0; i < 100; i++) {
                    atomicInteger.incrementAndGet();
                    j++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                end.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(100);
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(new IncrTask(start, end));
            t.start();
        }
        start.countDown();
        end.await();
        System.out.println("result is :" + j);
        System.out.println(atomicInteger.get());
    }
}