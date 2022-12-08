package threadcore.study.productconsumber.exam1;

/**
 * @author zhaoqw
 * @date 2022/12/08
 */
public class ProductTest {
    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();

        Thread producer1 = new Thread(new Producer(storage));
        Thread producer2 = new Thread(new Producer(storage));
        Thread consumer1 = new Thread(new Consumer(storage));
        Thread consumer2 = new Thread(new Consumer(storage));
        producer1.start();
        producer2.start();
        Thread.sleep(2000);
        consumer1.start();
        consumer2.start();
    }
}
