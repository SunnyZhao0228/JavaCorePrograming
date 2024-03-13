package threadcore.study.threadmethods;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂
 *
 * @author zhaoqw
 * @date 2023/12/26
 */
public class SimpleThreadFactory implements ThreadFactory {

    private final int maxThread;
    private final String threadGroupName;
    private final String threadNamePrefix;

    private final AtomicInteger poolCount = new AtomicInteger(0);
    private final AtomicInteger threadSeq = new AtomicInteger(0);

    private final ThreadGroup threadGroup;

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r,
                threadNamePrefix + threadSeq.getAndIncrement(),
                0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

    public SimpleThreadFactory(int maxThread, String threadGroupName, String threadNamePrefix) {
        this.maxThread = maxThread;
        this.threadNamePrefix = threadNamePrefix;
        this.threadGroupName = threadGroupName;
        this.threadGroup = new ThreadGroup(threadGroupName);
    }
}
