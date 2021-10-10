package threadcore.study.objectthreadmethods;

/**
 * 3个线程，线程1和线程2先被阻塞，线程3唤醒他们，
 * 看看用notify和notifyAll
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-10-08 22:13]
 */
public class WaitNotifyAll implements Runnable{
    private static final Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    resourceA.notifyAll();
                    //resourceA.notify();
                    System.out.println("ThreadC notified.");
                }
            }
        });

        threadA.start();
        threadB.start();
        //Thread.sleep(100);
        thread.start();

    }
    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "got resourceA lock");

            try{
                System.out.println(Thread.currentThread().getName() + "wait to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "is waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
