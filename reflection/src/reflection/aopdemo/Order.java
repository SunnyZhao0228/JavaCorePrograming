package reflection.aopdemo;

/**
 * @author zhaoqw
 * @date 2023/01/08
 */
public class Order implements IOrder {
    int state = 0;
    @Override
    public void pay() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.state = 1;
    }

    @Override
    public void show() {
        System.out.println("Order Status: " + this.state);
    }
}
