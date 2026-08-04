package threadcore.study.productconsumber.exam3;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeDataBuffer<T> {
    public static final int MAX_AMOUNT = 10;
    private List<T> dataList = new LinkedList<>();

    //保存数量
    private AtomicInteger amount = new AtomicInteger(0);

    public void put(T t) throws InterruptedException {
        synchronized (dataList) {
            while (amount.get() == MAX_AMOUNT) {
                System.out.println("数据已满，请等待...");
                dataList.wait();
            }
            dataList.add(t);
            amount.incrementAndGet();
            dataList.notifyAll();
        }
    }
    public T get() throws InterruptedException {
        synchronized (dataList) {
            while (amount.get() == 0) {
                System.out.println("数据已空，请等待...");
                dataList.wait();
            }
            T t = dataList.remove(0);
            amount.decrementAndGet();
            dataList.notifyAll();
            return t;
        }
    }
}
