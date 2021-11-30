package threadcore.study.threadSingleTom;

import threadcore.study.objectthreadmethods.SleepDontReleaseLock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-11-18 20:37]
 */
public class Mgr05 {
    private static volatile Mgr05 INSTANCE;
    private Mgr05() {}
    public static Mgr05 getInstance() throws InterruptedException {
        if (INSTANCE == null) {
            synchronized(Mgr05.class) {
                Thread.sleep(10);
                if (INSTANCE == null)
                    INSTANCE = new Mgr05();
            }
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4,8,5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 1000; i++) {
             new Thread(() -> {
                 try {
                     System.out.println(Mgr05.getInstance().hashCode());
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }).start();
        }



    }
}
