package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-26 19:28]
 */
public class LockInterruptibly implements Runnable{
    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread thread1 = new Thread(lockInterruptibly);
        Thread thread2 = new Thread(lockInterruptibly);
        thread1.start();
        thread2.start();

    }
    private Lock lock =  new ReentrantLock();
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁" );
        try{
            lock.lockInterruptibly();
            try{
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "睡眠期间被中断了");
            }finally {
                System.out.println(Thread.currentThread().getName() + "释放锁");
                lock.unlock();

            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "等待获取锁的期间被打断");
            e.printStackTrace();
        }
    }
}
