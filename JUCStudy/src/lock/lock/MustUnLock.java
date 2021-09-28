package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 不会像synchronized一样,异常的时候自动释放锁，
 * 所以最佳实践是，finally中释放锁，以保证异常发生时锁一定被释放
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-26 19:01]
 */
public class MustUnLock {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try{
            //获取本锁保护的资源
            System.out.println(Thread.currentThread().getName() + "开始执行任务");
        }finally {
            lock.unlock();
        }


    }
}
