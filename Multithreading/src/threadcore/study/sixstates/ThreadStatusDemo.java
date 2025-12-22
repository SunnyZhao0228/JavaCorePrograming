package threadcore.study.sixstates;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoqw
 * @version 1.0
 * @date 2025/12/22
 */
public class ThreadStatusDemo {
    // 每个线程执行的次数
    private static final int MAX_TURN = 10;

    //线程编号
    static int threadSeqNumber = 0;

    //全局线程列表
    static List<Thread> staticThreads = new ArrayList<>();


    // 输出线程列表中的线程状态
    public static void printThreadStatus() {
        staticThreads.forEach(thread -> {
            System.out.println(thread.getName() + " 线程状态：" + thread.getState());
        });
    }

    //向全局的静态线程列表加入线程
    private static void addStatusThread(Thread thread)
    {
        staticThreads.add(thread);
    }

    static class StatusDemoThread extends Thread
    {
        public StatusDemoThread()
        {
            super("statusPrintThread" + (++threadSeqNumber));
            //将自己加入到全局的静态线程列表
            addStatusThread(this);
        }

        public void run()
        {
            System.out.println(getName() + ", 状态为" + getState());
            for (int turn = 0; turn < MAX_TURN; turn++)
            {
                //线程睡眠
                try {
                    sleepMilliSeconds(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //输出所有线程的状态
                printThreadStatus();
            }
            System.out.println(getName() + "- 运行结束.");
        }

    }


    public static void sleepMilliSeconds(int millionTimes) throws InterruptedException {
        Thread.sleep(millionTimes);
    }

    public static void main(String[] args) throws InterruptedException {
        addStatusThread(Thread.currentThread());
        StatusDemoThread statusDemoThread1 = new StatusDemoThread();
        System.out.println(statusDemoThread1.getName()  + ":" + statusDemoThread1.getState());
        StatusDemoThread statusDemoThread2 = new StatusDemoThread();
        System.out.println(statusDemoThread2.getName()  + ":" + statusDemoThread2.getState());

        StatusDemoThread statusDemoThread3 = new StatusDemoThread();
        System.out.println(statusDemoThread3.getName()  + ":" + statusDemoThread3.getState());

        statusDemoThread1.start(); //启动第一个线程

        sleepMilliSeconds(500);//等待500ms启动第二个线程
        statusDemoThread2.start();

        sleepMilliSeconds(500);//等待 1000ms 启动第三个线程
        statusDemoThread3.start();
        sleepMilliSeconds(10000);//睡眠100秒


    }
}
