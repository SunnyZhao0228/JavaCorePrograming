package threadcore.study.objectthreadmethods;

import java.util.concurrent.locks.Lock;

/**
 *  用wait和 notify方法 实现 两个线程交替打印0-100的奇偶数
 */
public class WaitNotifyPrintOddEvenWait {
    private static int count = 0;
    private static final  Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new TurnRunner(),"偶数").start();
        new Thread(new TurnRunner(),"奇数").start();

    }

    static class TurnRunner implements Runnable{

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName()+":" + count++);
                    lock.notify();
                    if (count<=100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
