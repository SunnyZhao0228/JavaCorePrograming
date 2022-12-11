package threadcore.study.createthread;

/**
 * @author zhaoqw
 * @date 2022/9/7
 */
public class WelcomeApp {
    public static void main(String[] args) {
        Thread thread = new WelcomeThread();
        thread.start();

        System.out.println("1.Welcome ！ I‘m " + Thread.currentThread().getName());
    }

    static class WelcomeThread extends Thread {
        @Override
        public void run() {
            System.out.println("2.Welcome ！ I‘m " + Thread.currentThread().getName());
        }
    }
}
