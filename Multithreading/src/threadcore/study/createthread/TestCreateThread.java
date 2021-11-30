package threadcore.study.createthread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 启动线程的方式有哪几种？
 * new Thread().start
 *
 */
public class TestCreateThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread1 thread1 = new MyThread1();
        Thread thread2 = new Thread(new MyThread2());
        FutureTask task = new FutureTask(new MyThread3());
        Thread thread3 = new Thread(task);

        Thread thread4 = new Thread(() -> System.out.println("lambda 实现 Thread") );
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4,8,3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.AbortPolicy());

        pool.submit(task);
        System.out.println(task.get());

        thread1.start();
        thread2.start();
        thread4.start();

        pool.shutdown();
    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("继承thread类重写run方法");
    }
}

class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("实现runnable接口 实现run方法");
    }
}

class MyThread3 implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "call...............";
    }
}
