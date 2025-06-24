package threadcore.study.threadpool;

import java.util.concurrent.Callable;

/**
 * @author zhaoqw
 * @date 2025/6/23
 */
public class ShutdownHookThread extends Thread {

    ShutdownHookThread(String name, Callable<Void> callable) {
    }
}
