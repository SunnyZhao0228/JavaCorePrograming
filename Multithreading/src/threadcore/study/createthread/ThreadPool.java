package threadcore.study.createthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池创建线程的方法
 * 本质是用了一个ThreadFactory，还是用了new Thread()
 * 所以线程池不是新的创建线程的方式
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-11 8:36]
 */
public class ThreadPool {
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 10000000;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < MAX_TURN; i++) {
            executorService.submit(new Task());
        }
    }
}


class Task implements Runnable{
    private static AtomicLong atomicLong = new AtomicLong(0l);

    @Override
    public void run() {
        for (int i = 0; i < ThreadPool.COMPUTE_TIMES; i++) {
            atomicLong.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName() + atomicLong.get());
    }
}



