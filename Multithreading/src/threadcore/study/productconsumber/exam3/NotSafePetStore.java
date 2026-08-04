package threadcore.study.productconsumber.exam3;

import threadcore.study.productconsumber.exam3.entity.Goods;
import threadcore.study.productconsumber.exam3.entity.IGoods;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotSafePetStore {
    //数据缓冲区静态实例
    private static NotSafeDataBuffer<IGoods> notSafeDataBuffer =
            new NotSafeDataBuffer();

    // 生产者执行的动作
    static Callable<IGoods> producerAction = () ->
    {
        // 创建商品
        IGoods goods = null;
        try
        {
            goods = notSafeDataBuffer.put(Goods.produceOne());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    };

    //消费者执行的动作
    static Callable<IGoods> consumerAction = () ->
    {
        // 从PetStore获取商品
        IGoods goods = null;
        try
        {
            goods = notSafeDataBuffer.get();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return goods;
    };

    public static void main(String[] args) {
        final int THREAD_TOTAL = 20;
        //线程池，用于多线程模拟测试
        ExecutorService threadPool =
                Executors.newFixedThreadPool(THREAD_TOTAL);
        for (int i = 0; i < 5; i++)
        {
            //生产者实例每生产一个商品，间隔500ms
            threadPool.submit(new Producer(producerAction, 500));
            //消费者实例每消费一个商品，间隔1500ms
            threadPool.submit(new Consumer(consumerAction, 1500));
        }
    }
}
