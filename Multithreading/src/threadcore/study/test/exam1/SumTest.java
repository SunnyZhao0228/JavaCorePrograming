package threadcore.study.test.exam1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoqw
 * @date 2023/5/18
 */
public class SumTest {
    private static final int COUNT = 100000000;

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                8, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(12));
        // 保存返回值的FutureTask
        List<Future<Long>> futureList = new ArrayList<>();

        // 分成6个任务计算
        int i;
        for (i = 0; i < 5; i++) {
            SumTask sumTask = new SumTask(i * 16666666 + 1, (i + 1) * 16666666);
            System.out.printf("从%s, 到%s\n",i * 16666666 + 1, (i + 1) * 16666666);
            Future<Long> future = threadPoolExecutor.submit(sumTask);
            futureList.add(future);
        }
        // 处理结尾漏算的几个数
        SumTask sumTask = new SumTask(i * 16666666 + 1, COUNT);
        System.out.printf("从%s, 到%s\n",i * 16666666 + 1, (i + 1) * 16666666);
        Future<Long> future = threadPoolExecutor.submit(sumTask);
        futureList.add(future);

        // 产看有多少任务完成了
        do{
            System.out.printf("Main: 已经完成多少个任务: %d\n",threadPoolExecutor.getCompletedTaskCount());
            for (i = 0; i < futureList.size(); i++) {
                Future<Long> result = futureList.get(i);
                System.out.printf("Main: Task %d: %s\n",i, result.isDone());
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (threadPoolExecutor.getCompletedTaskCount() < futureList.size());


        // 收集结果
        Long reduce = futureList.stream().map(result -> {
            try {
                return result.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).reduce(0l, (aLong, aLong2) -> aLong + aLong2);
        System.out.println("总和=" + reduce);
    }
}
