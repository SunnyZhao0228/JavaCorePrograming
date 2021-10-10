package threadcore.study.objectthreadmethods;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-27 22:15]
 */
public class Wait {
    public static Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized(object) {
                System.out.println(Thread.currentThread().getName() + "开始执行");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized(object) {
                object.notify();
                System.out.println(Thread.currentThread().getName() + "调用了notify");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        Thread.sleep(100);
        thread2.start();
    }
}
