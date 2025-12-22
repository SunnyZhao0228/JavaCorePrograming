package threadcore.study.threadmethods;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 总结起来，Thread.yield（）方法的以下特点中：
 * （1）yield仅能使一个线程从运行状态转到就绪状态，而不是阻塞状态。
 * （2）yield不能保证使得当前正在运行的线程迅速转换到就绪状态。
 * （3）即使完成了迅速切换，系统通过线程调度机制从所有就绪线程中挑选下一条执行线程
 * 时，就绪的线程有可能被选中，也有可能不被选中，其调度的过程受到其他因素（如优先级）影响
 *
 * 也即是用了Yield方法也不定管用
 *
 * @author zhaoqw
 * @version 1.0
 * @date 2025/12/22
 */
public class ThreadYieldDemo {


    public static final int MAX_TURN = 100;//执行次数

    public static AtomicInteger index = new AtomicInteger(0);//执行编号

    // 记录线程的执行次数
    private static Map<String, AtomicInteger> metric = new HashMap<>();

    //输出线程的执行次数
    private static void printMetric()
    {
        System.out.println("metric = " + metric);
    }

    static class YieldThread extends Thread
    {
        static int threadSeqNumber = 1;
        public YieldThread()
        {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
            //将线程加入到执行次数统计map
            metric.put(this.getName(), new AtomicInteger(0));
        }

        public void run()
        {
            for (int i = 1; i < MAX_TURN && index.get() < MAX_TURN; i++)
            {
                System.out.println("线程优先级：" + getPriority());
                index.incrementAndGet();
                //统计一次
                metric.get(this.getName()).incrementAndGet();
                if (i % 2 == 0)
                {
                    //让步：出让执行的权限
                    Thread.yield();
                }
            }
            //输出所有线程的执行次数
            printMetric();
            System.out.println(getName() + " 运行结束.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new YieldThread();
        //设置为最高的优先级
        thread1.setPriority(Thread.MAX_PRIORITY);
        Thread thread2 = new YieldThread();
        //设置为最低的优先级
        thread2.setPriority(Thread.MIN_PRIORITY);
        System.out.println("启动线程.");
        thread1.start();
        thread2.start();
        Thread.sleep(1000);

    }
}
