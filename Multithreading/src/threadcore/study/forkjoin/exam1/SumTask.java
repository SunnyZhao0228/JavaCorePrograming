package threadcore.study.forkjoin.exam1;

import java.util.concurrent.RecursiveTask;

/**
 * 分任务求和
 *
 * @author zhaoqw
 * @date 2022/12/09
 */
public class SumTask extends RecursiveTask<Long> {

    private int start;
    private int end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static final int threadhold = 5;

    @Override
    protected Long compute() {
        Long sum = 0l;
        // 任务足够小，直接执行
        boolean canCompute = (end - start) <= threadhold;
        if (canCompute) {
            for (int i = start; i <= end ; i++) {
                sum += i;
            }
        } else {
            // 任务大于阈值，分列为两个任务
            int middle = (end - start) / 2;
            SumTask sumTask1 = new SumTask(start, middle);
            SumTask sumTask2 = new SumTask(middle + 1, end);
            invokeAll(sumTask1, sumTask2);

            Long  result1 = sumTask1.join();
            Long  result2 = sumTask1.join();
            sum = result1 + result2;
        }
        return sum;
    }
}
