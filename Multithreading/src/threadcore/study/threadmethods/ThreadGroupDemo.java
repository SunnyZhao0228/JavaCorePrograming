package threadcore.study.threadmethods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Thread构造器可以设置线程组，也可以设置线程名
 */
public class ThreadGroupDemo {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static List<Thread> DbConnThread() {
        ThreadGroup dbConnThreadGroup = new ThreadGroup("数据库连接线程组");
        List<Thread> dbConnThreadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(dbConnThreadGroup, () -> {
                System.out.println("线程名: " + Thread.currentThread().getName()
                        + ", 所在线程组: " + Thread.currentThread().getThreadGroup().getName());
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "db-conn-thread-" + i);
            dbConnThreadList.add(t);
        }
        return dbConnThreadList;
    }

    public static List<Thread> httpReqThread() {
        ThreadGroup httpReqThreadGroup = new ThreadGroup("第三方http请求线程组");
        List<Thread> httpReqThreadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(httpReqThreadGroup, new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程名: " + Thread.currentThread().getName()
                            + ", 所在线程组: " + Thread.currentThread().getThreadGroup().getName());
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "http-req-thread-" + i);
            httpReqThreadList.add(t);
        }
        return httpReqThreadList;
    }

    public static void startThread(List<Thread> threadList) {
        for (Thread thread : threadList) {
            thread.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> dbConnThreadList = DbConnThread();
        List<Thread> httpReqThreadList = httpReqThread();
        startThread(dbConnThreadList);
        startThread(httpReqThreadList);
        Thread.sleep(3000000);
    }
}