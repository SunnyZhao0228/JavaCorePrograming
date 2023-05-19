package threadcore.study.test.exam3;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoqw
 * @date 2023/5/19
 */
public class CountTask1 implements Callable<Integer> {
    //定义每个线程计算的区间
    private int startIndex;
    private int endIndex;

    private volatile int[] arr;


    public CountTask1(int startIndex, int endIndex, int[] arr) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.arr = arr;
    }

    @Override
    public Integer call() throws Exception {
        Integer count = 0;
        for(int i = startIndex; i < endIndex; i++) {
            if (arr[i] == 50) count++;
        }
        return count;
    }
}
