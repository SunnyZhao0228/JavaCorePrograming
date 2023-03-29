package threadcore.study.deadlock;

/**
 * @author zhaoqw
 * @date 2023/02/15
 */
public class FixedDeadLockTest {
    // 创建两个资源
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        FixedDeadLockTest fixedDeadLockTest = new FixedDeadLockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                fixedDeadLockTest.methodA();
            }).start();
            new Thread(()-> {
                fixedDeadLockTest.methodB();
            }).start();
        }


    }


    public void methodA() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "get ResourceA");
            try {
                System.out.println(Thread.currentThread().getName() + "waiting ResourceB");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (resourceB) {
            System.out.println(Thread.currentThread().getName() + "get ResourceB");
        }
    }

    public void methodB() {
        synchronized (resourceB) {
            System.out.println(Thread.currentThread().getName() + "get ResourceB");
            try {
                System.out.println(Thread.currentThread().getName() + "waiting ResourceA");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "get ResourceA");
        }
    }
}
