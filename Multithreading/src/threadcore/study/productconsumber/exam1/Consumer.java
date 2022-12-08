package threadcore.study.productconsumber.exam1;

/**
 * 消费者
 *
 * @author zhaoqw
 * @date 2022/12/08
 */
public class Consumer implements Runnable {
    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            ++i;
            storage.pop();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
