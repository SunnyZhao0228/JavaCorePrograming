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

    private final AtomicInteger count = new AtomicInteger(0);
    private final AtomicInteger threadSeq = new AtomicInteger(0);

    private final ThreadGroup threadGroup;

    @Override
    public Thread newThread(Runnable r) {
        return null;
    }

    public SimpleThreadFactory(int maxThread, String threadGroupName, String threadNamePrefix) {
        this.maxThread = maxThread;
        this.threadNamePrefix = threadNamePrefix;
        this.threadGroupName = threadGroupName;
        this.threadGroup = new ThreadGroup(threadGroupName);
    }
}
