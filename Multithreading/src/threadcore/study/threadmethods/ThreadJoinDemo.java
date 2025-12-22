package threadcore.study.threadmethods;


/**
 * @author zhaoqw
 * @version 1.0
 * @date 2025/12/22
 */
public class ThreadJoinDemo {
    public static final int SLEEP_GAP = 5000;//睡眠时长
    public static final int MAX_TURN = 50;//睡眠次数

    static class SleepThread extends Thread {
        static int threadSeqNumber = 1;

        public SleepThread()
        {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        public void run()
        {
            try
            {
                System.out.println(getName() + " 进入睡眠.");
                // 线程睡眠一会
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
                System.out.println(getName() + " 发生被异常打断.");
                return;
            }
            System.out.println(getName() + " 运行结束.");
        }
    }


    public static void main(String args[])
    {
        Thread thread1 = new SleepThread();
        System.out.println("启动 thread1.");
        thread1.start();
        try {
            thread1.join();//合并线程1，不限时, Thread1来插一脚,需要等我执行完
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("启动 thread2.");
        //启动第二条线程，并且进行限时合并，等待时间为1秒
        Thread thread2 = new SleepThread();
        thread2.start();
        try {
            thread2.join(1000);//限时合并，限时1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程运行结束.");
    }
}
