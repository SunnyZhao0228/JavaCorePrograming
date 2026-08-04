package threadcore.study.productconsumber.exam3;

import threadcore.study.productconsumber.exam3.entity.IGoods;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NotSafeDataBuffer<T> {
    public static final int MAX_AMOUNT = 10;
    private List<T> dataList = new LinkedList<>();

    //保存数量
    private AtomicInteger amount = new AtomicInteger(0);

    public T put(T t) throws InterruptedException {
        while (amount.get() == MAX_AMOUNT) {
            System.out.println("数据已满，请等待...");
        }
        dataList.add(t);
        amount.incrementAndGet();
        return t;
    }

    public T get() throws InterruptedException {
        while (amount.get() == 0) {
            System.out.println("数据已空，请等待...");
        }
        T t = dataList.remove(0);
        amount.decrementAndGet();
        return t;
    }
}
