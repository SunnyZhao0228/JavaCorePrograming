package threadcore.study.productconsumber.exam1;

import java.util.Random;

/**
 * 生产者
 *
 * @author zhaoqw
 * @date 2022/12/08
 */
public class Producer implements Runnable {
    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int i = 0;
        Random r = new Random();
        while (i < 10) {
            i++;
            Product product = new Product(i, "产品" + r.nextInt(2000));
            storage.push(product);
        }
    }
}
