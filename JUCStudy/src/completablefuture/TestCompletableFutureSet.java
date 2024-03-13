package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhaoqw
 * @date 2024/3/1
 */
public class TestCompletableFutureSet {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS,
            AVAILABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 1. 创建CompletableFuture
        CompletableFuture<String> future = new CompletableFuture();
        POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                // 2. 休眠，模拟任务
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()  + "set future result .... ");
                future.complete("hello future");
            }
        });
        // 3. 等待结果
        System.out.println("-------------main thread ---------------");
        // future.get() 同步阻塞
        System.out.println(future.get());
        System.out.println(future.get(1000, TimeUnit.MICROSECONDS));
        System.out.println("---------main thread get future ---------");
    }

    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // 休眠，模拟任务
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("over.................");
            }
        }, POOL_EXECUTOR);
        System.out.println(future.get());
    }
}
