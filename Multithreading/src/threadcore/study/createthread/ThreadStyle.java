package threadcore.study.createthread;

/**
 * 继承Thread 重写run方法
 */
public class ThreadStyle extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("继承Thread类 重写run方法");
    }

    public static void main(String[] args) {
        ThreadStyle threadStyle = new ThreadStyle();
        threadStyle.start();
    }

}
