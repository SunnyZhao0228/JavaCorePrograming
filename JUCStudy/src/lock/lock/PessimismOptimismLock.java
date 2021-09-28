package lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-26 20:30]
 */
public class PessimismOptimismLock {
    int a;
    public synchronized void testMethod() {
        a++;
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
}
