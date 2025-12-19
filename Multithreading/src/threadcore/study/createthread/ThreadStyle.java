package threadcore.study.createthread;

/**
 * 继承Thread 重写run方法
 */
public class ThreadStyle extends Thread{
    //线程的编号
    static int threadNo = 1;

    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    public ThreadStyle() {
        super("DemoThread-" + threadNo ++);
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println(getCurrentThreadName() + ": iterator-" + i);
        }
        System.out.println(getCurrentThreadName() + ": end");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new ThreadStyle().start();
        }
    }

}
