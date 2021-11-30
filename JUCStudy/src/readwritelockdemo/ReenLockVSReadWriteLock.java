package readwritelockdemo;

import com.sun.jdi.event.ThreadStartEvent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-10-24 22:24]
 */
public class ReenLockVSReadWriteLock {
    private static ReentrantLock rlock = new ReentrantLock();
    public static int value;

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("readover");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try{
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("writeover");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable read = () -> read(readLock);
        Runnable write = () -> write(writeLock,new Random().nextInt(1000));

        for (int i = 0; i < 18; i++) {
            new Thread(read).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(write).start();
        }

    }


}
