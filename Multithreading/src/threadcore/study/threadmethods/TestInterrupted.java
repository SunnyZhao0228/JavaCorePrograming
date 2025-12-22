package threadcore.study.threadmethods;


import static threadcore.study.sixstates.ThreadStatusDemo.sleepMilliSeconds;

/**
 * @author zhaoqw
 * @version 1.0
 * @date 2025/12/22
 */
public class TestInterrupted {
    public static final int SLEEP_GAP = 5000;//睡眠时长
    public static final int MAX_TURN = 50;//睡眠次数
    static class SleepThread extends Thread
    {
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

    public void testInterrupted2() throws InterruptedException {
        Thread thread = new Thread()
        {
            public void run()
            {
                System.out.println("线程启动了");
               //一直循环
                while (true)
                {
                    System.out.println(isInterrupted());
                    try {
                        sleepMilliSeconds(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    //如果线程被中断，退出死循环
                    if (isInterrupted())
                    {
                        System.out.println("线程结束了");
                        return;
                    }
                }
            }
        };
        thread.start();
        Thread.sleep(2000);//等待2秒
        thread.interrupt(); //中断线程
        Thread.sleep(2000);//等待2秒
        thread.interrupt();
    }

    public static void main(String args[]) throws InterruptedException {

        Thread thread1 = new SleepThread();
        thread1.start();
        Thread thread2 = new SleepThread();
        thread2.start();

        Thread.sleep(2000);//等待2秒
        thread1.interrupt(); //打断线程1

        Thread.sleep(5000);//等待2秒
        thread2.interrupt();  //打断线程2，此时线程2已经终止

        Thread.sleep(1000);//等待1秒
        System.out.println("程序运行结束............");


        new TestInterrupted().testInterrupted2();
    }
}
