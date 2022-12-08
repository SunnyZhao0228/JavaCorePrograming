package threadcore.study.productconsumber.exam1;

/**
 * 库存
 *
 * @author zhaoqw
 * @date 2022/12/08
 */
public class Storage {
    private Product[] products = new Product[20];
    private int top = 0;

    // 生产者生产产品
    public synchronized void push(Product product) {
        // 满了就等着
        while (top == products.length) {
            try {
                System.out.println("product wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 没满就放商品
        products[top++] = product;
        System.out.println(Thread.currentThread().getName() + "生产了产品" + product);
        System.out.println("Producer NotifyAll");
        notifyAll();
    }

    public synchronized Product pop() {
        while (top == 0) {
            try {
                System.out.println("comsumer wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 消费商品
        --top;
        Product product = new Product(products[top].getId(), products[top].getName());
        products[top] = null;
        System.out.println(Thread.currentThread().getName() + "消费了产品" + product);
        System.out.println("Consumer NotifyAll");
        notifyAll();
        return product;
    }
}
