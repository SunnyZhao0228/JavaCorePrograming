package juc.util;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoqw
 * @date 2023/02/04
 */
public class SemaphoreSample1 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            final int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 尝试获取一个信号量, 5s内，获取到返回true
                        if (semaphore.tryAcquire(5, TimeUnit.SECONDS)) {
                            play();
                            semaphore.release(); // 执行完成之后，释放这个信号量，"从跑道上退出去"
                        } else {
                            System.out.println(new Date() + "  " + Thread.currentThread().getName() + "服务器已满");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

//                    if (semaphore.tryAcquire()) { // 尝试获取一个信号量，获取到返回true
//                        play();
//                        semaphore.release(); // 执行完成之后，释放这个信号量，"从跑道上退出去"
//                    } else {
//                        System.out.println(new Date() + "  " + Thread.currentThread().getName() + "服务器已满");
//                    }
//
//
//                    try {
//                        semaphore.acquire(); // 获取一个信号量，“占用一个跑道”
//                        play();
//                        semaphore.release(); // 执行完成之后，释放这个信号量，"从跑道上退出去"
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                }
            });
        }
    }

    public static void play() {
        try {
            System.out.println(new Date() + "  " + Thread.currentThread().getName() + "获得进入服务器的资格");
            Thread.sleep(2000);
            System.out.println(new Date() + "  " + Thread.currentThread().getName() + "退出服务器");
            Thread.sleep(1000);
        } catch (Exception e)  {
            e.printStackTrace();
        }
    }
}
