package volatiledemo;

/**
 * @author zhaoqw
 * @date 2024/3/13
 */
public class Counter {
    private volatile long count;

    public long value() {
        return this.count;
    }

    public void increase() {
        synchronized (this) {
           count ++;
        }
    }
}
