package threadcore.study.productconsumber.exam3;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer  implements Runnable{
    public static final int PRODUCE_GAP = 200;

    //总次数
    static final AtomicInteger TURN = new AtomicInteger(0);

    //生产者对象编号
    static final AtomicInteger PRODUCER_NO = new AtomicInteger(1);

    //生产者名称
    String name = null;

    //生产的动作
    Callable action = null;

    int gap = PRODUCE_GAP;

    public Producer(Callable action, int gap)
    {
        this.action = action;
        this.gap = gap;
        name = "生产者-" + PRODUCER_NO.incrementAndGet();

    }

    @Override
    public void run() {
        while (true) {
            try{
                Object call = action.call();
                if (call != null) {
                    System.out.println(name + "生产了：" + call);
                }
                Thread.sleep(gap);
                // 增加总次数
                TURN.incrementAndGet();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
