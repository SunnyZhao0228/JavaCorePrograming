package threadcore.study.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoqw
 * @date 2025/6/23
 */
public class ThreadUtils {
    //CPU 核数
    private static final int CPU_COUNT =
            Runtime.getRuntime().availableProcessors();
    //IO 处理线程数
    private static final int IO_MAX = Math.max(2, CPU_COUNT * 2);
    /**
     * 空闲保活时限，单位秒
     */
    private static final int KEEP_ALIVE_SECONDS = 30;
    /**
     * 有界队列 size
     */
    private static final int QUEUE_SIZE = 128;
    //懒汉式单例创建线程池：用于 IO 密集型任务
    private static class IoIntenseTargetThreadPoolLazyHolder{
        private static final ThreadPoolExecutor INSTANCE =
                new ThreadPoolExecutor(
                        CPU_COUNT ,
                        IO_MAX,
                        KEEP_ALIVE_SECONDS,
                        TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(QUEUE_SIZE),
                        new CustomThreadFactory("io-intense-target-thread-pool"));
        static {
            INSTANCE.allowCoreThreadTimeOut(true);
            Runtime.getRuntime().addShutdownHook(new ShutdownHookThread("IO 密集型任务线程池",
                    new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            INSTANCE.shutdown();
                            INSTANCE.awaitTermination(KEEP_ALIVE_SECONDS, TimeUnit.SECONDS);
                            return null;
                        }
                    }));
        }
    }
}
