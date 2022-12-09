package threadcore.study.exercise;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1-100000000 从1+2+3 + ... + 100000000
 *  均分为6个任务，没个任务不能超过1的误差
 *  例如 1 - 10 分3个任务
 *  1-3 4-6 6-10 不允许
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-11-20 9:19]
 */
public class JUCTest02 {

    static int count = Constants.COUNT;
    static int threadCount = Constants.THREAD_COUNT;


    static class MyTask implements Callable<Long> {
        private int start;
        private int end;

        public MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            System.out.println(start + "-" + end + ",result  " + sum);

            return sum;
        }
    }

    public synchronized static long answer() {
        long sum = 0;
        for (int i = 0; i <= 100000000; i++) {
            sum += i;
        }
        return sum;
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        FutureTask[] futureTasks = new FutureTask[Constants.THREAD_COUNT];
        long sum = 0;
        int gap = count / threadCount;
        for (int i = 0; i < futureTasks.length; i++) {
            FutureTask futureTask = null;
            int fromInt = Constants.COUNT * i / Constants.THREAD_COUNT + 1;
            int toInt = Constants.COUNT * (i + 1) / Constants.THREAD_COUNT;
            futureTask = new FutureTask(new MyTask(fromInt, toInt));
            futureTasks[i] = futureTask;
            threadPoolExecutor.submit(futureTask);
        }

        for (FutureTask futureTask : futureTasks) {
            try {
                sum += (long) futureTask.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(sum);
        System.out.println(answer());
        threadPoolExecutor.shutdown();
    }
}



