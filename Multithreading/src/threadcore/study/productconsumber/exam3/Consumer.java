package threadcore.study.productconsumber.exam3;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable{
    //消费的时间间隔，默认等待100毫秒
    public static final int CONSUME_GAP = 100;
    //消费总次数
    static final AtomicInteger TURN = new AtomicInteger(0);
    //消费者对象编号
    static final AtomicInteger CONSUMER_NO = new AtomicInteger(1);
    //消费者名称
    String name;
    //消费的动作
    Callable action = null;

    //消费一次等待的时间，默认为100ms
    int gap = CONSUME_GAP;

    public Consumer(Callable action, int gap)
    {
        this.action = action;
        this.gap = gap;
        name = "消费者-" + CONSUMER_NO.incrementAndGet();

    }

    @Override
    public void run() {
        while (true) {
            //增加消费次数
            TURN.incrementAndGet();
            try {
                //执行消费动作
                Object out = action.call();
                if (null != out) {
                    System.out.println("第" + TURN.get() + "轮消费：" + out);
                }
                //每一轮消费之后，稍微等待一下
                Thread.sleep(gap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
