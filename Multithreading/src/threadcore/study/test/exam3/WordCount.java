package threadcore.study.test.exam3;

import threadcore.study.test.exam2.SumTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * 词语频率统计
 *
 * @author zhaoqw
 * @date 2023/5/19
 */
public class WordCount {
    private static int[] arr = new int[10000];

    static {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100 + 1);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        int result1 = count50();
        long end = System.currentTimeMillis();
        System.out.printf("串行计算50出现次数%d, 用时%s\n", result1 ,end - start);

        start = System.currentTimeMillis();
        int result2 = count50ForExecutor();
        end = System.currentTimeMillis();
        System.out.printf("Executor线程池计算50出现次数%d, 用时%s\n", result2 ,end - start);

        start = System.currentTimeMillis();
        int result3 = count50ForFockJoinPool();
        end = System.currentTimeMillis();
        System.out.printf("FockJoinPool线程池计算50出现次数%d, 用时%s\n", result3 ,end - start);

    }


    public static int count50() {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 50) count++;
        }
        return count;
    }


    public static int count50ForExecutor() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                8, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(12));
        // 保存返回值的FutureTask
        List<Future<Integer>> resultList = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < 10; i++) {
            CountTask1 countTask = new CountTask1(i * 1000, (i + 1) * 1000, arr);
            Future<Integer> future = threadPoolExecutor.submit(countTask);
            resultList.add(future);
        }

        for (int i = 0; i < resultList.size(); i++) {
            Integer integer = resultList.get(i).get(1, TimeUnit.SECONDS);
            count += integer;
        }

        threadPoolExecutor.shutdown();
        return count;
    }


    public static int count50ForFockJoinPool() throws ExecutionException, InterruptedException {
        //创建执行线程池
        ForkJoinPool pool = new ForkJoinPool(8);
        //ForkJoinPool pool = new ForkJoinPool(4);

        //创建任务
        CountTask2 task = new CountTask2(0, 9999, arr);

        //提交任务
        ForkJoinTask<Integer> result = pool.submit(task);

        //等待结果
        do {
            System.out.printf("Main: Thread Count: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Paralelism: %d\n",pool.getParallelism());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        //输出结果
        Integer count = result.get();
        System.out.println(count);
        return count;
    }
}
