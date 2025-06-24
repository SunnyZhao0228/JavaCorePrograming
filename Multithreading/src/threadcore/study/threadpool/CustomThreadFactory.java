package threadcore.study.threadpool;

import java.util.concurrent.ThreadFactory;

/**
 * @author zhaoqw
 * @date 2025/6/23
 */
public class CustomThreadFactory implements ThreadFactory {

    private String THREAD_NAME_PREFIX = "CustomThread-";
    @Override
    public Thread newThread(Runnable r) {
        return null;
    }

    public CustomThreadFactory(String threadNamePrefix) {
        this.THREAD_NAME_PREFIX = threadNamePrefix;
    }
}
