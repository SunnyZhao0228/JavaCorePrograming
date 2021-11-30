package threadcore.study.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 淘宝面试题
 * 实现一个容器，提供两个方法add，size，写两个线程
 * 线程1,添加10个元素到容器中。
 * 线程2,实时控制元素个数，当个数到5个时，线程2给出提示并结束。
 */
public class TBTest01_WaitNotify {
    volatile List list = new ArrayList();
    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        TBTest01_WaitNotify c = new TBTest01_WaitNotify();
        final Object lock = new TBTest01_WaitNotify();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 启动");
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                lock.notify();
            }

        },"t2").start();


        new Thread(() -> {
            System.out.println("t1 启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                    if (c.size() == 5) {
                        lock.notify();

                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1 结束");
            }
        },"t1").start();
    }
}
