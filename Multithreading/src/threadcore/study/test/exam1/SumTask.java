package threadcore.study.test.exam1;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author zhaoqw
 * @date 2023/5/18
 */
public class SumTask implements Callable<Long> {
    //定义每个线程计算的区间
    private int startNumber;
    private int endNumber;

    public SumTask(int startNumber, int endNumber){
        this.startNumber=startNumber;
        this.endNumber=endNumber;
    }

    @Override
    public Long call() throws Exception {
        Long sum = 0l;
        for(int i = startNumber; i <= endNumber; i++) {
            sum += i;
        }

        Thread.sleep(new Random().nextInt(1000));
        System.out.printf("%s: %d\n",Thread.currentThread().getName(), sum);
        return sum;
    }
}
