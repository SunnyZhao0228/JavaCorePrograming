package threadcore.study.test.exam3;

import threadcore.study.test.exam2.SumTask;

import java.util.concurrent.RecursiveTask;

/**
 * @author zhaoqw
 * @date 2023/5/19
 */
public class CountTask2 extends RecursiveTask<Integer> {
    //定义每个线程计算的区间
    private int startIndex;
    private int endIndex;

    private int[] arr;

    public static final int threadhold = 5;

    public CountTask2(int startIndex, int endIndex, int[] arr) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        Integer count = 0;
        // 如果任务足够小, 就直接执行
        boolean canCompute = endIndex - startIndex <= threadhold;
        if (canCompute) {
            for (int i = startIndex; i <= endIndex; i++) {
                if (arr[i] == 50) count++;
            }
        } else {
            // 任务大于阈值，拆分任务进行计算
            int middle = (startIndex + endIndex) / 2;
            CountTask2 subTask1 = new CountTask2(startIndex, middle, arr);
            CountTask2 subTask2 = new CountTask2(middle + 1, endIndex, arr);

            invokeAll(subTask1, subTask2);

            // 获取结果
            Integer sum1 = subTask1.join();
            Integer sum2 = subTask2.join();

            count = sum1 + sum2;
        }
        return count;
    }
}
