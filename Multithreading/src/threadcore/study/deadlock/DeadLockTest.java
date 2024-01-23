package threadcore.study.deadlock;

/**
 * 死锁案例
 *
 *
 * @author zhaoqw
 * @date 2022/12/07
 */
public class DeadLockTest {
    // 创建两个资源
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        // 线程A拿到资源A 等待资源B
        // 线程B拿到资源B 等待资源A
        // 就互相等对方释放资源
        // 这里用的synchronized，用Reentrantlock也是一样的
        Thread threadA = new Thread(() -> {
           synchronized (resourceA) {
               System.out.println(Thread.currentThread().getName() + "get ResourceA");
               try {
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(Thread.currentThread().getName() + "waiting ResourceB");
               synchronized (resourceB) {
                   System.out.println(Thread.currentThread().getName() + "get ResourceB");
               }
           }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println(Thread.currentThread().getName() + "get ResourceB");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "waiting ResourceA");
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + "get ResourceA");
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
