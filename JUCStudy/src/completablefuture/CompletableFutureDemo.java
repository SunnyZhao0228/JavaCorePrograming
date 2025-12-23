package completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class CompletableFutureDemo {
    public static final int COUNT = 10;
    public static final int ITERATIONS = 10000;
    public static final ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) {
        // 创建自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                COUNT,
                COUNT * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new ThreadFactory() {
                    private final java.util.concurrent.atomic.AtomicInteger threadNumber = new java.util.concurrent.atomic.AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r, "Worker-" + threadNumber.getAndIncrement());
                        return t;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        AtomicLong sum = new AtomicLong(0);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        // 提交任务到线程池
        for (int i = 0; i < COUNT; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    lock.lock();
                    try {
                        sum.incrementAndGet();
                        // 这里可以执行其他需要保护的操作
                    } finally {
                        lock.unlock();
                    }
                }
            }, executor);
            futures.add(future);
        }

        // 等待所有任务完成并处理结果
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> {
                    System.out.println("All threads finished");
                    System.out.println("sum: " + sum);
                })
                .exceptionally(throwable -> {
                    System.err.println("Error occurred: " + throwable.getMessage());
                    throwable.printStackTrace();
                    return null;
                })
                .join();

        // 关闭线程池
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
