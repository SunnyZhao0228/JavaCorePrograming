package threadcore.study.sixstates;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-25 11:31]
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new BlockedWaitingTimedWaiting());
        thread1.start();
        Thread thread2 = new Thread(new BlockedWaitingTimedWaiting());
        thread2.start();

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
    }
}
