package threadcore.study.stopthreads;

/**
 * 最佳实践2：在catch子语句中调用Thread.currentThread().interrupt() 来恢复中断状态，
 * 以便后续的执行中，依旧能够检查到刚才发生了中断
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-19 23:42]
 *
 */
public class RightWayStopThreadProd2 implements Runnable{

    @Override
    public void run() {
        while(true) {
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupt 程序运行结束");
                break;
            }
            System.out.println("go");
            reInterrupt();


        }
    }

    private void reInterrupt()  {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadProd2());
        thread.start();


        Thread.sleep(1000);
        thread.interrupt();

    }
}
