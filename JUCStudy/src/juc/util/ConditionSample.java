package juc.util;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoqw
 * @date 2023/02/04
 */
public class ConditionSample {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        new Thread(() -> {
            lock.lock(); // 加锁
            try {
                c1.await(); // 阻塞当前线程
                Thread.sleep(1000);
                System.out.println("100000000004");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock(); // 加锁
            try {
                c2.await(); // 阻塞当前线程
                Thread.sleep(1000);
                System.out.println("100000000003");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock(); // 加锁
            try {
                c3.await(); // 阻塞当前线程
                Thread.sleep(1000);
                System.out.println("100000000002");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock(); // 加锁
            try {
                Thread.sleep(1000);
                System.out.println("100000000001");
                c3.signal();
                c2.signal();
                c1.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
