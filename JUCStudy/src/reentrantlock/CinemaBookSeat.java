package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-26 22:34]
 */
public class CinemaBookSeat {
    public static ReentrantLock lock = new ReentrantLock();

    private static void bookSeat() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "开始预订座位");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "完成预订座位");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
    }
}
