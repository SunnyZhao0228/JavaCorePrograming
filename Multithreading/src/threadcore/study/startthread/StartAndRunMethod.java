package threadcore.study.startthread;

/**
 *  run方法和start方法
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
                System.out.println(Thread.currentThread().getName());
        };

        // 调用Run等于调用一般方法
        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();


    }
}
