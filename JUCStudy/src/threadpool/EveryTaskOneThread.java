package threadpool;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-11 21:49]
 */
public class EveryTaskOneThread {
    public static void main(String[] args) {

        for (int j = 0; j < 100; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    EveryTaskOneThread.task();
                }
            }).start();
        }
    }

    /**
     * 为每一个任务都分配一个线程，浪费资源
     */
    public static void task() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }
    }
}




