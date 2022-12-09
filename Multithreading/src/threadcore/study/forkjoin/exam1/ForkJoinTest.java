package threadcore.study.forkjoin.exam1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author zhaoqw
 * @date 2022/12/09
 */
public class ForkJoinTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        // 创建任务
        SumTask sumTask = new SumTask(1, 10000000);

        // 提交任务
        ForkJoinTask<Long> submit = forkJoinPool.submit(sumTask);

        // 等待结果
        do {
            System.out.printf("Main: Thread Count: %d\n",forkJoinPool.getActiveThreadCount());
            System.out.printf("Main: Paralelism: %d\n",forkJoinPool.getParallelism());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!submit.isDone());

        System.out.println(submit.get().toString());
    }
}
