package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-13 13:25]
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            executorService.submit(new MyTask());
        }
    }



}

class MyTask implements Runnable{
    static AtomicInteger taskNo = new AtomicInteger(1);
    private String taskName;
    public MyTask()
    {
        taskName = "task-" + taskNo.get();
        taskNo.incrementAndGet();
    }

    @Override
    public void run() {
        try {
            System.out.println("任务：" + taskName + " doing");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}