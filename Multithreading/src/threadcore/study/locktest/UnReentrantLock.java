package threadcore.study.locktest;

/**
 * 设计一个简单的不可重入锁
 *
 * @author zhaoqw
 * @date 2023/02/15
 */
public class UnReentrantLock {

    private boolean isLocked = false;

    public synchronized void lock() {
        System.out.println("进入Lock加锁:" + Thread.currentThread().getName());

        // 判断是否已经被加锁
        while (isLocked) {
            System.out.println("进入Wait等待:" + Thread.currentThread().getName());
        }
    }


    public synchronized void unlock() {

    }
}
